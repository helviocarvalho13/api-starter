package com.ingaia.apistarter.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ingaia.apistarter.model.UserDB;
import com.ingaia.apistarter.services.UserDBService;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDBService userDBService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserDB> userDBOptional = userDBService.returnUserDBPorEmail(email);
		UserDB userDB = userDBOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new UserSystem(userDB, getPermissoes(userDB));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(UserDB userDB) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		userDB.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return authorities;
	}	

}
