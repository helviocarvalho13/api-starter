package com.pulse.apistarter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.apistarter.model.Cart;
import com.pulse.apistarter.model.CartShipping;

@Repository
public interface CartShippingRepository extends JpaRepository<CartShipping, Long>{
	
	Optional<CartShipping> findByCart(Cart cart);

}
