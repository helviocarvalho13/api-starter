package com.ingaia.apistarter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VW_STATISTIC")
@Getter
@Setter
public class Statistic {
	
	@Id
	@Column(name = "CODE")
	private Long code;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "TIMES_SEARCHED")
	private int timesSeached;
	
}
