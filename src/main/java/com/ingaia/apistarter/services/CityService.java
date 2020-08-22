package com.ingaia.apistarter.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingaia.apistarter.model.City;
import com.ingaia.apistarter.model.openweather.Weather;
import com.ingaia.apistarter.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepo;
	
	@Transactional
	public void save(Weather weather) {
		City city = new City();
		city.setCity(weather.getCity());
		city.setCountry(weather.getCountry());
		city.setId(weather.getId());
		cityRepo.save(city);
		
	}
}
