package com.pulse.apistarter.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CART_SHIPPING")
@Getter
@Setter
public class CartShipping extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = -7064460265525611987L;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CART_CODE")
	private Cart cart;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SHIPPING_CODE")
	private Shipping shipping;
	
	@Column(name="TOTAL_PRICE")
	private BigDecimal totalPrice;
	
	@Column(name="DELIVERY_TIME")
	private String deliveryTime;

}
