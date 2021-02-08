package com.pulse.apistarter.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pulse.apistarter.model.Address;
import com.pulse.apistarter.model.Shipping;
import com.pulse.apistarter.model.dto.ShippingRateDTO;
import com.pulse.apistarter.repository.AddressRepository;
import com.pulse.apistarter.repository.ShippingRepository;

@Service
public class ShippingRateService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ShippingRepository shippingRepository;

	@Transactional(readOnly = true)
	public List<ShippingRateDTO> returnShippingRates(Long addressCode) {
		Optional<Address> optionalAddress = addressRepository.findById(addressCode);
		
		if(optionalAddress.isPresent()) {
			List<ShippingRateDTO> shippingRateDTOList = new ArrayList<ShippingRateDTO>();
			List<Shipping> arrayShipping = shippingRepository.findAll();
			
			arrayShipping.forEach(s -> shippingRateDTOList.add(new ShippingRateDTO(s, 
					"15 dias úteis", new BigDecimal(15), addressCode)));
			
			return shippingRateDTOList;
		}else {
			throw new EmptyResultDataAccessException("O registro não foi encontrado", 1);
		}
		
	}

	
}
