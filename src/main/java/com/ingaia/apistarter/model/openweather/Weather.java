package com.ingaia.apistarter.model.openweather;

import org.springframework.dao.EmptyResultDataAccessException;

import com.ingaia.apistarter.model.openweather.json.WeatherJSON;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {
	private String temperatureC;
	private String city;
	private String country;
	private String id;
	
	public Weather(WeatherJSON weatherJSON) {
		this.temperatureC = weatherJSON.getMain().getTemp();
		this.city = weatherJSON.getName();
		this.country = weatherJSON.getSys().getCountry();
		this.id = weatherJSON.getSys().getId();
	}
	
	public Weather() {}
	
	public Double doubleTemperatureC() {
		if(this.temperatureC != null) {
			return new Double(this.temperatureC);
		}
		return null;
	}
	
	public String categoriaSpotify() {
		
		if(this.temperatureC == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Double temperatureC = this.doubleTemperatureC();
		
		return temperatureC < 10.0 ? "classical" : temperatureC <= 25.0 ? "rock" : "pop"; 
				
	}
}
