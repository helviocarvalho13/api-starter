package com.ingaia.apistarter.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_DB")
@Getter
@Setter
public class UserDB extends AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7794690314775783568L;

	@NotNull(message = "Nome é obrigatório.")
	@Column(name="NAME")
	private String name;
	
	@NotNull(message = "Email é obrigatório.")
	@Column(name="EMAIL")
	private String email;
	
	@NotNull(message = "Senha é obrigatório.")
	@Column(name="PASSWORD")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_DB_PERMISSION", joinColumns = @JoinColumn(name = "code_user_db")
		, inverseJoinColumns = @JoinColumn(name = "code_permission"))
	private List<Permission> permissions;

}
