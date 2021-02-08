package com.pulse.apistarter.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
public class Product extends AbstractEntity implements Serializable{
	
	private static final long serialVersionUID = -4575399883781989627L;

	@Column(name="NAME")
	private String name;
	
	@Column(name="PRICE")
	private BigDecimal price;
	
	@Column(name="QUANTITY_STOCK")
	private Integer quantityStock;
	
	@Transient
	private Integer quantityCart;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CATEGORY_CODE")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Category category;
	
}
