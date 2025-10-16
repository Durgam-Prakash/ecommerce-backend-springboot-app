package com.ecommerce.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.constant.AuthConstants;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.enums.UserRole;
import com.ecommerce.backend.pojo.SignupData;
import com.ecommerce.backend.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;
	

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public User signup(SignupData signupData) throws Exception {
		
		
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
		
		User saveUser = userRepository.save(user);
		return saveUser;
		
		
	}

}
