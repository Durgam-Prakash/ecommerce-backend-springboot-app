package com.ecommerce.backend.service;

import java.util.List;
import java.util.Optional;

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
	
	public List<Address> viewAddress(int userId) throws Exception {
		
		List<Address> dbOptional = addressRepository.findByUserId(userId);
		
		if(dbOptional.isEmpty()) {
			throw new Exception("Address  not found by this ID : " + userId);
		}
		
		return dbOptional;
	}
	
	public List<Address> viewALlAddress() {
		List<Address> allAddresses = addressRepository.findAll();
		return allAddresses;
	}
	
	
	public void deleteADdress(int userId,int addressId) throws Exception {
		Optional<Address> dbOptional = addressRepository.findByUserIdAndAddressId(userId, addressId);
		
		if(dbOptional.isEmpty()) {
			throw new Exception("No address found with address ID: " + addressId + " and user ID: " + userId);
		}
		
		addressRepository.deleteById(addressId);
		
	}
	

}
