package com.pulse.apistarter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pulse.apistarter.model.Payment;
import com.pulse.apistarter.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Transactional(readOnly = true)
	public List<Payment> returnPaymentsTypes() {
		return paymentRepository.findAll();
	}

}
