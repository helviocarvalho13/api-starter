package com.pulse.apistarter.model.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CloseCartDTO {
	
	private Long codeCart;
	private Long codePayment;
	private Long codeShipping;
	private BigDecimal totalPrice;
	private String deliveryTime;

}
