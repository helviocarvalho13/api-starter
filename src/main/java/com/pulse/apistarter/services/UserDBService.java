package com.pulse.apistarter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pulse.apistarter.model.Address;
import com.pulse.apistarter.model.UserDB;
import com.pulse.apistarter.model.dto.AddressDTO;
import com.pulse.apistarter.repository.UserDBRepository;

@Service
public class UserDBService {
	
	@Autowired
	private UserDBRepository userDBRepository;
	
	@Transactional(readOnly = true)
	public Optional<UserDB> returnUserDBPorEmail(String email){
		return userDBRepository.findByEmail(email);
	}

	@Transactional(readOnly = true)
	public List<AddressDTO> returnAdresses(Long userDBCode) {
		Optional<UserDB> optionalUserDB = userDBRepository.findById(userDBCode);
		if(optionalUserDB.isPresent()) {
			List<Address> adresses = optionalUserDB.get().getAdresses();
			List<AddressDTO> adressesDTO = new ArrayList<>();
			adresses.forEach(a -> adressesDTO.add(new AddressDTO(a)));
			return adressesDTO;
		}else{
			throw new EmptyResultDataAccessException("O registro não foi encontrado", 1);
		}
		
	}

}
