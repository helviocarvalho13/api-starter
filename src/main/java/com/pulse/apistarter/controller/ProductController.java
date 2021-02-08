package com.pulse.apistarter.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.apistarter.model.Product;
import com.pulse.apistarter.model.dto.ProductDTO;
import com.pulse.apistarter.services.ProductService;

@RestController	
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/v1/public/products")
	public List<Product> list(){
		List<Product> lista = productService.returnProducts();
		return lista;
	}
	
	@PostMapping("/v1/private/products")
	@PreAuthorize("hasAuthority('ROLE_PRODUCT') and #oauth2.hasScope('/products')")
	public ResponseEntity<Long> create(@Valid @RequestBody ProductDTO productDTO){
		Long productCode = productService.createProduct(productDTO);
		return ResponseEntity.ok(productCode);
	}
	
	@PutMapping("/v1/private/products/{code}")
	@PreAuthorize("hasAuthority('ROLE_PRODUCT') and #oauth2.hasScope('/products')")
	public ResponseEntity<Long> update(@NotNull @PathVariable(value = "code") Long productCode, @Valid @RequestBody ProductDTO productDTO){
		productCode = productService.updateProduct(productCode, productDTO);
		return ResponseEntity.ok(productCode);
	}
	
	@DeleteMapping("/v1/private/products/{code}")
	@PreAuthorize("hasAuthority('ROLE_PRODUCT') and #oauth2.hasScope('/products')")
	public ResponseEntity<Object> delete(@NotNull @PathVariable(value = "code") Long productCode){
		HttpStatus status = productService.deleteProduct(productCode);
		return new ResponseEntity<>(status);
	}
	
}
