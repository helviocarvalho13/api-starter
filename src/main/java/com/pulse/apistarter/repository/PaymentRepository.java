package com.pulse.apistarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.apistarter.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
