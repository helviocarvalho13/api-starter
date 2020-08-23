package com.ingaia.apistarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingaia.apistarter.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
