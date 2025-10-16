package com.ecommerce.backend.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.constant.AuthConstants;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.enums.UserRole;
import com.ecommerce.backend.pojo.LoginData;
import com.ecommerce.backend.pojo.SignupData;
import com.ecommerce.backend.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public Map<String, Object> signup(SignupData signupData) throws Exception {
		
		
		Optional<User> dbOptional = userRepository.findByEmailId(signupData.getEmailId());
		
		if(dbOptional.isPresent()) {
			throw new Exception(AuthConstants.USER_ALREADY_EXISTS);
		}
		
		User user = new User();
		user.setFirstName(signupData.getFirstName());
		user.setLastName(signupData.getLastName());
		user.setEmailId(signupData.getEmailId());
		user.setPasswordHash(passwordEncoder.encode(signupData.getPassword()));
		user.setPhoneNumber(signupData.getPhoneNumber());
		user.setRole(UserRole.BUYER);
		
		System.out.println(user);
		//User saveUser = userRepository.save(user);
		//return saveUser;
		
		user = userRepository.save(user);
		String token = jwtService.generateJwtToken(user);
		Map<String, Object> response = new HashMap<>();
		response.put("userData", user);
		response.put("token", token);
		return response;
		
	}
	
	
	public Map<String, Object> userLogin(LoginData logindata) throws Exception {
		
		Optional<User> dbOptional = userRepository.findByEmailId(logindata.getEmailId());
		
		if(dbOptional.isEmpty()) {
			throw new Exception("User is not found..! Please signup ");
		}
		
		
		User user = dbOptional.get();
		if(passwordEncoder.matches(logindata.getPassword(), user.getPasswordHash())==false) {
			throw new Exception("Pasword is invalid");
		}
		
		
		String token = jwtService.generateJwtToken(user);
		
		Map<String, Object> response = new HashMap<>();
		response.put("userData",user);
		response.put("token",token);
		return response;
		
	}

}
