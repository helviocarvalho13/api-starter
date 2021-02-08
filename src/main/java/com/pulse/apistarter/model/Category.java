package com.pulse.apistarter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
public class Category extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = -6578075661214908921L;
	
	@Column(name="NAME")
	private String name;

}
