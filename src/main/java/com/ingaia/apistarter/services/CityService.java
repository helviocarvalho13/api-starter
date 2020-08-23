package com.ingaia.apistarter.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ingaia.apistarter.model.City;
import com.ingaia.apistarter.model.openweather.Weather;
import com.ingaia.apistarter.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepo;
	
	@Transactional
	public City save(Weather weather) {
		City city = new City();
		city.setCity(weather.getCity());
		city.setCountry(weather.getCountry());
		city.setId(weather.getId());
		City c = cityRepo.save(city);
		return c;
	}
	
}
