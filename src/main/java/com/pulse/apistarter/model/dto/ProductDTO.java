package com.pulse.apistarter.model.dto;

import java.math.BigDecimal;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	@NotNull
	private String name;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private Integer quantityStock;
	
	@NotNull
	private Long codeCategory;
}
