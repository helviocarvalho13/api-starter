package com.pulse.apistarter.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProductDTO {
	
	@NotNull
	private Long codeProduct;
	
	@NotNull
	private Long codeCart;
	
	@NotNull
	@Min(value = 1)
	private Integer quantity;

}
