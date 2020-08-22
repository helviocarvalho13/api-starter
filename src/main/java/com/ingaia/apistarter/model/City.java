package com.ingaia.apistarter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CITY")
@Getter
@Setter
public class City extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 846502855844445342L;
	
	@Column(name="ID")
	private String id;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="COUNTRY")
	private String country;
}
