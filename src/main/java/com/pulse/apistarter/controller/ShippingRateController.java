package com.pulse.apistarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.apistarter.model.dto.ShippingRateDTO;
import com.pulse.apistarter.services.ShippingRateService;

@RestController
public class ShippingRateController {
	
	@Autowired
	private ShippingRateService shippingRateService; 

	@GetMapping("/v1/public/shippingRates/{code}")
	public List<ShippingRateDTO> list(@PathVariable(value = "code") Long addressCode){
		List<ShippingRateDTO> lista = shippingRateService.returnShippingRates(addressCode);
		return lista;
	}
}
