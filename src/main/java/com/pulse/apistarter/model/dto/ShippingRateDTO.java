package com.pulse.apistarter.model.dto;

import java.math.BigDecimal;

import com.pulse.apistarter.model.Shipping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingRateDTO {
	
	private Long codeShipping;
	
	private String transportCompany;
	
	private String deliveryTime;
	
	private BigDecimal totalPrice;
	
	private Long codeAddress;
	
	public ShippingRateDTO () {}
	
	public ShippingRateDTO(Shipping shipping, String deliveryTime, 
			BigDecimal totalPrice, Long codeAddress) {
		this.codeShipping = shipping.getCode();
		this.transportCompany = shipping.getName();
		this.deliveryTime = deliveryTime;
		this.totalPrice = totalPrice;
		this.codeAddress = codeAddress;
	}
}
