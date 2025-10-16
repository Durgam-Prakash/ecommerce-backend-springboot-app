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
import com.ecommerce.backend.pojo.ForgotPasswordSendOTP;
import com.ecommerce.backend.pojo.LoginData;
import com.ecommerce.backend.pojo.PasswordUpdateAfterReset;
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
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginData loginData) throws Exception{
		Map<String, Object> userLogin = authService.userLogin(loginData);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "You have logged in successfully");
		response.put(ExceptionConstants.API_DATA, userLogin);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + userLogin.get("token").toString());
		
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
	}
	
	
	

	@PostMapping("/forgot-password/send-otp")
	public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordSendOTP forgotPasswordSendOTP) throws Exception{
		 authService.forgotPasswordSendOtp(forgotPasswordSendOTP);
		
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "OTP send to you mail please check your mail inbox");
		response.put(ExceptionConstants.API_DATA, "");
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@PostMapping("/forgot-password/update-password")
	public ResponseEntity<?> updatePasswordAfterReset(@Valid @RequestBody PasswordUpdateAfterReset  passwordUpdateAfterReset) throws Exception{
		 
		authService.passwordUpdateAfterReset(passwordUpdateAfterReset);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "Your Pasword has been updated successfully....Please Login");
		response.put(ExceptionConstants.API_DATA, "");
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
