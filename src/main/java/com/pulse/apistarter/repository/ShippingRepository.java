package com.pulse.apistarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.apistarter.model.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long>{

}
