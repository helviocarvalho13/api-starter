package com.pulse.apistarter.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
	
	private BigDecimal totalPrice;
	
	@NotNull
	private Long codeUserDB;
	
	private Long codePayment;
	
}
