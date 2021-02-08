package com.pulse.apistarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.apistarter.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
