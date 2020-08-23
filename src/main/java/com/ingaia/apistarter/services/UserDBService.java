package com.ingaia.apistarter.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingaia.apistarter.model.UserDB;
import com.ingaia.apistarter.repository.UserDBRepository;

@Service
public class UserDBService {
	
	@Autowired
	private UserDBRepository userDBRepository;
	
	public Optional<UserDB> returnUserDBPorEmail(String email){
		return userDBRepository.findByEmail(email);
	}

}
