package com.pulse.apistarter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CART_PRODUCT")
@Getter
@Setter
public class CartProduct extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = -4583632270928439574L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CART_CODE")
	private Cart cart;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRODUCT_CODE")
	private Product product;
	
	@Column(name="QUANTITY")
	private Integer quantity;

}
