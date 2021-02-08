package com.pulse.apistarter.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pulse.apistarter.model.CartProduct;
import com.pulse.apistarter.model.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptDTO {

	private String client;
	
	private String email;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private List<Product> products;
	
	private BigDecimal totalProducts;
	
	private BigDecimal totalShipping;
	
	private BigDecimal totalReceipt;
	
	private String deliveryTime;
	
	public ReceiptDTO () {}

	public ReceiptDTO(String client, String email, List<Product> products, BigDecimal totalProducts,
			BigDecimal totalShipping, BigDecimal totalReceipt, String deliveryTime) {
		super();
		this.client = client;
		this.email = email;
		this.products = products;
		this.totalProducts = totalProducts;
		this.totalShipping = totalShipping;
		this.totalReceipt = totalReceipt;
		this.deliveryTime = deliveryTime;
	}
	
	public ReceiptDTO(Map<Long, Integer> qtdProduct, String client, String email, List<Product> products, BigDecimal totalProducts,
			BigDecimal totalShipping, BigDecimal totalReceipt, String deliveryTime) {
		super();
		this.client = client;
		this.email = email;
		this.products = products;
		this.totalProducts = totalProducts;
		this.totalShipping = totalShipping;
		this.totalReceipt = totalReceipt;
		this.deliveryTime = deliveryTime;
		this.products.forEach(p -> p.setQuantityCart(qtdProduct.get(p.getCode())));
	}
	
}
