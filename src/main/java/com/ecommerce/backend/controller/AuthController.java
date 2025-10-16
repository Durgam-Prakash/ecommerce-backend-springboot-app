package com.ecommerce.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.constant.ExceptionConstants;
import com.ecommerce.backend.pojo.SignupData;
import com.ecommerce.backend.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@Valid @RequestBody SignupData signupData) throws Exception{
		
		 Map<String, Object> signup = authService.signup(signupData);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "New User signup (or) Created Successfully");
		response.put(ExceptionConstants.API_DATA, signup);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + signup.get("token").toString());
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(response);
	}
	
	
	
	

}
