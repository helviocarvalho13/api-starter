package com.pulse.apistarter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ADDRESS")
@Getter
@Setter
public class Address extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = -4838630731800709643L;
	
	@Column(name="ZIP_CODE")
	private String zipCode;
	
	@Column(name="STREET")
	private String street;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="COUNTRY")
	private String country;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CODE_USER_DB")
	private UserDB userDB;

}
