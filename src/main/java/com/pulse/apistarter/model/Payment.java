package com.pulse.apistarter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PAYMENT")
@Getter
@Setter
public class Payment extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1353918865691250885L;
	
	@Column(name="NAME")
	private String name;

}
