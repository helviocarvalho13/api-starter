package com.pulse.apistarter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.apistarter.model.Cart;
import com.pulse.apistarter.model.CartProduct;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long>{
	
	Optional<List<CartProduct>> findByCart(Cart cart);

}
