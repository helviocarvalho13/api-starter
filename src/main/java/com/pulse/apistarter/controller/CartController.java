package com.pulse.apistarter.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.apistarter.model.dto.CartDTO;
import com.pulse.apistarter.model.dto.CartProductDTO;
import com.pulse.apistarter.model.dto.CloseCartDTO;
import com.pulse.apistarter.model.dto.ReceiptDTO;
import com.pulse.apistarter.services.CartService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping("/v1/private/carts")
	@PreAuthorize("hasAuthority('ROLE_CART') and #oauth2.hasScope('/carts')")
	public ResponseEntity<Long> create(@Valid @RequestBody CartDTO cartDTO){
		Long cartCode = cartService.createCart(cartDTO);
		return ResponseEntity.ok(cartCode);
	}
	
	@PostMapping("/v1/private/carts/product")
	@PreAuthorize("hasAuthority('ROLE_CART') and #oauth2.hasScope('/carts')")
	public ResponseEntity<Long> addProduct(@Valid @RequestBody CartProductDTO cartProductDTO){
		Long cartCode = cartService.addProduct(cartProductDTO);
		return ResponseEntity.ok(cartCode);
	}
	
	@DeleteMapping("/v1/private/carts/product/{code}")
	@PreAuthorize("hasAuthority('ROLE_CART') and #oauth2.hasScope('/carts')")
	public ResponseEntity<Object> withdrawProduct(@NotNull @PathVariable(value = "code") Long cartProductCode){
		HttpStatus status = cartService.withdrawProduct(cartProductCode);
		return new ResponseEntity<>(status);
	}
	
	@PostMapping("/v1/private/carts/close")
	@PreAuthorize("hasAuthority('ROLE_CART') and #oauth2.hasScope('/carts')")
	public ResponseEntity<ReceiptDTO> closeCart(@Valid @RequestBody CloseCartDTO closeCartDTO) throws JRException, FileNotFoundException{
		ReceiptDTO receiptDTO = cartService.closeCart(closeCartDTO);
				
		return ResponseEntity.ok(receiptDTO);
	}
	
	@GetMapping("/v1/public/carts/report/{code}")
	public void report(HttpServletResponse response, @NotNull @PathVariable(value = "code") Long cartCode) throws IOException, JRException{
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"nota_fiscal.pdf\""));
		OutputStream out = response.getOutputStream();
		cartService.exportReport(cartCode, out);	
	}
	
}
