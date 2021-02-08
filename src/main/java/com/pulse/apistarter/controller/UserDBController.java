package com.pulse.apistarter.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pulse.apistarter.model.dto.AddressDTO;
import com.pulse.apistarter.services.UserDBService;

@RestController	
public class UserDBController {
	
	@Autowired
	private UserDBService userDBService;

	@GetMapping("/v1/private/user/{code}/adresses")
	@PreAuthorize("hasAuthority('ROLE_USER') and #oauth2.hasScope('/users')")
	public List<AddressDTO> list(@NotNull @PathVariable(value = "code") Long userDBCode){
		List<AddressDTO> lista = userDBService.returnAdresses(userDBCode);
		return lista;
	}
	
}
