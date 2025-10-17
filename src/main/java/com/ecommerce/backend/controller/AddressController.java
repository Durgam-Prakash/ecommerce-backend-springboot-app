package com.ecommerce.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.constant.ExceptionConstants;
import com.ecommerce.backend.entity.Address;
import com.ecommerce.backend.pojo.AddressAddData;
import com.ecommerce.backend.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping("/add")
	public ResponseEntity<?> address(@Valid @RequestBody AddressAddData addressAddData){
		
		Address address = addressService.addAddress(addressAddData);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "Address Added successfully");
		response.put(ExceptionConstants.API_DATA, address);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
