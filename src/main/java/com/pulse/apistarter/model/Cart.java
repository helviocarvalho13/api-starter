package com.pulse.apistarter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CART")
@Getter
@Setter
public class Cart extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 8507098112515449614L;
	
	@Column(name="TOTAL_PRICE")
	private BigDecimal totalPrice;
	
	@NotNull
	@Column(name="DATE")
	private LocalDateTime date;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_DB_CODE")
	private UserDB userDB;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PAYMENT_CODE")
	private Payment payment;
	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="CART_PRODUCT", joinColumns=
    {@JoinColumn(name="CART_CODE")}, inverseJoinColumns=
      {@JoinColumn(name="PRODUCT_CODE")})
	private List<CartProduct> cartProducts;

}
