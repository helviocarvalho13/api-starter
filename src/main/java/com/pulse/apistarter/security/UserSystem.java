package com.pulse.apistarter.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.pulse.apistarter.model.UserDB;

public class UserSystem extends User {

	private static final long serialVersionUID = 1L;

	private UserDB userDB;

	public UserSystem(UserDB userDB, Collection<? extends GrantedAuthority> authorities) {
		super(userDB.getEmail(), userDB.getPassword(), authorities);
		this.userDB = userDB;
	}

	public UserDB getUserDB() {
		return userDB;
	}

}
