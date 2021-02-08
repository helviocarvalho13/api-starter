package com.pulse.apistarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.apistarter.model.Payment;
import com.pulse.apistarter.services.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/v1/public/payments")
	public List<Payment> list(){
		List<Payment> lista = paymentService.returnPaymentsTypes();
		return lista;
	}

}
