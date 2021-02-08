package com.pulse.apistarter.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pulse.apistarter.model.Category;
import com.pulse.apistarter.model.Product;
import com.pulse.apistarter.model.dto.ProductDTO;
import com.pulse.apistarter.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Cacheable("returnProducts")
	@Transactional(readOnly = true)
	public List<Product> returnProducts() {
		return productRepository.findAll();
	}

	@Transactional
	public Long createProduct(ProductDTO productDTO) {
		Product product = ajustarVinculos(productDTO);
		Long productCode = productRepository.save(product).getCode();
		return productCode;
	}

	private Product ajustarVinculos(ProductDTO productDTO) {
		Product product = new Product();
		
		Category category = new Category();
		category.setCode(productDTO.getCodeCategory());
		
		product.setCategory(category);
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantityStock(productDTO.getQuantityStock());
		return product;
	}

	@Transactional
	public Long updateProduct(Long productCode, @Valid ProductDTO productDTO) {
		Optional<Product> oldProduct = productRepository.findById(productCode);
		
		if(oldProduct.isPresent()) {
			Product product = oldProduct.get();
			Product newProduct = ajustarVinculos(productDTO);
			
			product.setCategory(newProduct.getCategory());
			product.setName(newProduct.getName());
			product.setPrice(newProduct.getPrice());
			product.setQuantityStock(newProduct.getQuantityStock());
			
			productCode = productRepository.save(product).getCode();
			
			return productCode;
		}else {
			throw new EmptyResultDataAccessException("O registro não foi encontrado", 1);
		}
		
	}

	@Transactional
	public HttpStatus deleteProduct(Long productCode) {
		Optional<Product> product = productRepository.findById(productCode);
		
		if(product.isPresent()) {
			productRepository.delete(product.get());
			return HttpStatus.OK;
		}else {
			throw new EmptyResultDataAccessException("O registro não foi encontrado", 1);
		}
		
	}

}
