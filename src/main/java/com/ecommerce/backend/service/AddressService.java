package com.ecommerce.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.entity.Address;
import com.ecommerce.backend.pojo.AddressAddData;
import com.ecommerce.backend.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address addAddress(AddressAddData addressAddData) {
		Address address = new Address();
		address.setUserId(addressAddData.getUserId());
		address.setAddressLine1(addressAddData.getAddressLine1());
		address.setAddressLine2(addressAddData.getAddressLine2());
		address.setCity(addressAddData.getCity());
		address.setState(addressAddData.getState());
		address.setCountry(addressAddData.getCountry());
		address.setPinCode(addressAddData.getPincode());
		address.setLongitude(addressAddData.getLongitude());
		address.setLatitude(addressAddData.getLatitude());
		address.setIsDefault(addressAddData.getIsDefault());
		
		address.setAddressType(addressAddData.getAddressType());
		
		Address saveAddress = addressRepository.save(address);
		return saveAddress;
		
	}

}
