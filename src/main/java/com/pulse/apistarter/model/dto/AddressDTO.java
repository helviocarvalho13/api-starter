package com.pulse.apistarter.model.dto;

import com.pulse.apistarter.model.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
	
	private String zipCode;
	
	private String street;
	
	private String city;
	
	private String country;
	
	private Long codeUserDB;
	
	public AddressDTO() {}
	
	public AddressDTO(Address address) {
		this.city = address.getCity();
		this.codeUserDB = address.getUserDB().getCode();
		this.country = address.getCountry();
		this.street = address.getStreet();
		this.zipCode = address.getZipCode();
	}

}
