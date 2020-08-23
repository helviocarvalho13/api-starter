package com.ingaia.apistarter.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ingaia.apistarter.model.openweather.Weather;
import com.ingaia.apistarter.model.openweather.json.WeatherJSON;

@Service
public class OpenWeatherService {
	
	@Value("${apiKeyOpenWeather}")
	private String apiKeyOpenWeather;
	
	public Weather returnWeather(String cidade) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://api.openweathermap.org/data/2.5/weather?q="+cidade+"&APPID="+apiKeyOpenWeather+"&units=metric";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<?> request = new HttpEntity<>(headers);
		ResponseEntity<WeatherJSON> weatherJSON = null;
		try {
			weatherJSON = restTemplate.exchange(url, HttpMethod.GET, request, WeatherJSON.class);
		}catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
		
		if(weatherJSON != null) {
			if(weatherJSON.getStatusCode().equals(HttpStatus.OK)) {
				return new Weather(weatherJSON.getBody());
			}
		}
		
		throw new EmptyResultDataAccessException(1);
		
	}
}
