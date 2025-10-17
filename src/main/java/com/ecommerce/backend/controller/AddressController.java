package com.ecommerce.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<?> address(@Valid @RequestBody AddressAddData addressAddData) {

		Address address = addressService.addAddress(addressAddData);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "Address Added successfully");
		response.put(ExceptionConstants.API_DATA, address);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{userId}/view")
	public ResponseEntity<?> viewAddress(@PathVariable int userId) throws Exception {

		List<Address> viewAddress = addressService.viewAddress(userId);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "your Address ");
		response.put(ExceptionConstants.API_DATA, viewAddress);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@GetMapping("/view")
	public ResponseEntity<?> viewAllAddress()  {

		List<Address> viewALlAddress = addressService.viewALlAddress();
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "All Address details  ");
		response.put(ExceptionConstants.API_DATA, viewALlAddress);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	
	@DeleteMapping("/{userId}/{addressId}/delete")
	public ResponseEntity<?> deleteAddress(@PathVariable int userId, @PathVariable int addressId) throws Exception {

		addressService.deleteADdress(userId, addressId);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "your Address deleted.. ");
		response.put(ExceptionConstants.API_DATA, "");

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
