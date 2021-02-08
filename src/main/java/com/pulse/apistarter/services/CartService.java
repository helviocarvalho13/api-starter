package com.pulse.apistarter.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.pulse.apistarter.model.Cart;
import com.pulse.apistarter.model.CartProduct;
import com.pulse.apistarter.model.CartShipping;
import com.pulse.apistarter.model.Payment;
import com.pulse.apistarter.model.Product;
import com.pulse.apistarter.model.Shipping;
import com.pulse.apistarter.model.UserDB;
import com.pulse.apistarter.model.dto.CartDTO;
import com.pulse.apistarter.model.dto.CartProductDTO;
import com.pulse.apistarter.model.dto.CloseCartDTO;
import com.pulse.apistarter.model.dto.ReceiptDTO;
import com.pulse.apistarter.repository.CartProductRepository;
import com.pulse.apistarter.repository.CartRepository;
import com.pulse.apistarter.repository.CartShippingRepository;
import com.pulse.apistarter.repository.PaymentRepository;
import com.pulse.apistarter.repository.ProductRepository;
import com.pulse.apistarter.repository.ShippingRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	@Autowired
	private CartProductRepository cartProductRepository;
	
	@Autowired
	private CartShippingRepository cartShippingRepository;

	@Transactional
	public Long createCart(CartDTO cartDTO) {
		Cart cart = ajustarVinculos(cartDTO);
		Long cartCode = cartRepository.save(cart).getCode();
		return cartCode;
	}
	
	private Cart ajustarVinculos(CartDTO cartDTO) {
		Cart cart = new Cart();
		
		UserDB userDB = new UserDB();
		userDB.setCode(cartDTO.getCodeUserDB());
		cart.setUserDB(userDB);
		
		if(cartDTO.getCodePayment() != null) {
			Payment payment = new Payment();
			payment.setCode(cartDTO.getCodePayment());
			cart.setPayment(payment);
		}
		
		cart.setDate(LocalDateTime.now());
		cart.setTotalPrice(cartDTO.getTotalPrice());
		
		return cart;
	}

	@Transactional
	public Long addProduct(CartProductDTO cartProductDTO) {
		Optional<Cart> optionalCart = cartRepository.findById(cartProductDTO.getCodeCart());
		Optional<Product> optionalProduct = productRepository.findById(cartProductDTO.getCodeProduct());
		
		if(optionalCart.isPresent() && optionalProduct.isPresent()) {
			
			CartProduct cartProduct = new CartProduct();
			Product product = optionalProduct.get();
			
			Cart cart = optionalCart.get();
			cart.setTotalPrice(addTotalPrice(cart, product, cartProductDTO.getQuantity()));
			cartProduct.setCart(cart);
			cartProduct.setProduct(product);
			cartProduct.setQuantity(cartProductDTO.getQuantity());
			
			cartRepository.save(cart);
			Long cartCode = cartProductRepository.save(cartProduct).getCart().getCode();
			return cartCode;
		}else {
			throw new EmptyResultDataAccessException("O registro não foi encontrado", 1);
		}
		
	}

	private BigDecimal addTotalPrice(Cart cart, Product product, Integer quantity) {
		BigDecimal total = cart.getTotalPrice() == null ? new BigDecimal(0) : cart.getTotalPrice();
		return total = total.add(product.getPrice().multiply(new BigDecimal(quantity)));
	}
	
	private BigDecimal subtractTotalPrice(Cart cart, Product product, Integer quantity) {
		BigDecimal total = cart.getTotalPrice() == null ? new BigDecimal(0) : cart.getTotalPrice();
		return total = total.subtract(product.getPrice().multiply(new BigDecimal(quantity)));
	}

	@Transactional
	public HttpStatus withdrawProduct(Long cartProductCode) {
		Optional<CartProduct> cartProduct = cartProductRepository.findById(cartProductCode);
		
		if(cartProduct.isPresent()) {
			Optional<Cart> optionalCart = cartRepository.findById(cartProduct.get().getCart().getCode());
			Optional<Product> optionalProduct = productRepository.findById(cartProduct.get().getProduct().getCode());

			if(optionalCart.isPresent() && optionalProduct.isPresent()) {
				Cart cart = optionalCart.get();
				cart.setTotalPrice(subtractTotalPrice(optionalCart.get(), optionalProduct.get(), cartProduct.get().getQuantity()));
				cartRepository.save(cart);
				cartProductRepository.delete(cartProduct.get());
				return HttpStatus.OK;
			}else {
				throw new EmptyResultDataAccessException("O registro não foi encontrado", 1);
			}
		}else {
			throw new EmptyResultDataAccessException("O registro não foi encontrado", 1);
		}
	}

	@Transactional
	public ReceiptDTO closeCart(@Valid CloseCartDTO closeCartDTO) {
		Optional<Cart> optionalCart = cartRepository.findById(closeCartDTO.getCodeCart());
		Optional<Payment> optionalPayment = paymentRepository.findById(closeCartDTO.getCodePayment());
		Optional<Shipping> optionalShipping = shippingRepository.findById(closeCartDTO.getCodeShipping());

		if(optionalCart.isPresent() && optionalPayment.isPresent() && optionalShipping.isPresent()) {
			Cart cart = optionalCart.get();
			Payment payment = optionalPayment.get();
			Shipping shipping = optionalShipping.get();
			cart.setPayment(payment);
			cartRepository.save(cart);
			
			CartShipping cartShipping = new CartShipping();
			cartShipping.setCart(cart);
			cartShipping.setShipping(shipping);
			cartShipping.setTotalPrice(closeCartDTO.getTotalPrice());
			cartShipping.setDeliveryTime(closeCartDTO.getDeliveryTime());
			cartShippingRepository.save(cartShipping).getCart().getCode();
			
			List<Product> arrayProducts = new ArrayList<>();
			cart.getCartProducts().forEach(cp -> arrayProducts.add(cp.getProduct()));
			
			ReceiptDTO receiptDTO = new ReceiptDTO(cart.getUserDB().getName(), cart.getUserDB().getEmail(),
					arrayProducts, cart.getTotalPrice(), cartShipping.getTotalPrice(), 
					cart.getTotalPrice().add(cartShipping.getTotalPrice()), cartShipping.getDeliveryTime());
			
			return receiptDTO;
		}else {
			throw new EmptyResultDataAccessException("O registro não foi encontrado", 1);
		}
		
	}

	@Transactional(readOnly = true)
	public void exportReport(@NotNull Long cartCode, OutputStream out) throws JRException, FileNotFoundException {
		Optional<Cart> optionalCart = cartRepository.findById(cartCode);
		JasperPrint jasperPrint = exportPdf(optionalCart);
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);		
	}

	private JasperPrint exportPdf(Optional<Cart> optionalCart) throws FileNotFoundException, JRException {
		File file = ResourceUtils.getFile("classpath:reports/NotaFiscal.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		Cart cart = optionalCart.get();
		
		List<Product> arrayProducts = new ArrayList<>();
		optionalCart.get().getCartProducts().forEach(cp -> arrayProducts.add(cp.getProduct()));
		CartShipping cartShipping = cartShippingRepository.findByCart(cart).get();
		List<CartProduct> cartProducts = cartProductRepository.findByCart(cart).get();
		
		Map<Long, Integer> qtdProduct = new HashMap<>();
		cartProducts.forEach(cp -> qtdProduct.put(cp.getProduct().getCode(), cp.getQuantity()));
		
		ReceiptDTO receiptDTO = new ReceiptDTO(qtdProduct, optionalCart.get().getUserDB().getName(), optionalCart.get().getUserDB().getEmail(),
				arrayProducts, optionalCart.get().getTotalPrice(), cartShipping.getTotalPrice(), 
				optionalCart.get().getTotalPrice().add(cartShipping.getTotalPrice()), cartShipping.getDeliveryTime());
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("totalReceipt", receiptDTO.getTotalReceipt());
		parameters.put("client", receiptDTO.getClient());
		parameters.put("email", receiptDTO.getEmail());
		parameters.put("totalProducts", receiptDTO.getTotalProducts());
		parameters.put("totalReceipt", receiptDTO.getTotalReceipt());
		parameters.put("totalShipping", receiptDTO.getTotalShipping());
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(receiptDTO.getProducts(), false);
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		return jasperPrint;
	}
	

}
