package com.pulse.apistarter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SHIPPING")
@Getter
@Setter
public class Shipping extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = -8592884339323782373L;

	@Column(name="NAME")
	private String name;
	
}
