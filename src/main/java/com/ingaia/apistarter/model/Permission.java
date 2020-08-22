package com.ingaia.apistarter.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERMISSION")
@Getter
@Setter
public class Permission extends AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4295085949411023553L;
	
	@NotNull(message = "Descrição é obrigatório.")
	@Column(name="DESCRIPTION")
	private String description;

}
