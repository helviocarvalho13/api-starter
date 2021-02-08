package com.pulse.apistarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.apistarter.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
