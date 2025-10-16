package com.ecommerce.backend.pojo;

import com.ecommerce.backend.constant.AuthConstants;
import com.ecommerce.backend.constant.RegexConstatnts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupData {
	
	@NotBlank(message = AuthConstants.ERROR_FIRST_NAME_REQUIRED)
	@Size(min = 3,message = AuthConstants.ERROR_FIRST_NAME_MIN_3_CHARACTERS)
	private String firstName;
	
	@NotBlank(message = AuthConstants.ERROR_LAST_NAME_REQUIRED)
	@Size(min = 3,message = AuthConstants.ERROR_LAST_NAME_MIN_3_CHARACTERS)
	private String lastName;
	
	@NotBlank(message = AuthConstants.ERROR_EMAI_ID_REQUIRED)
	@Pattern(regexp = RegexConstatnts.EMAIL_REGEX,message = AuthConstants.ERROR_EMAIL_INVALID)
	private String emailId;
	
	@NotBlank(message = AuthConstants.ERROR_PASSWORD_REQUIRED)
	@Size(min = 6,message = AuthConstants.ERROR_PASSWORD_MIN_6_CHARACTERS)
	private String password;
	
	@Pattern(regexp = RegexConstatnts.MOBILE_REGEX, message = AuthConstants.ERROR_PHONE_NUMBER_INVALID)
	@NotBlank(message = AuthConstants.ERROR_PHONE_NUMBER_REQUIRED)
	private String phoneNumber;

}
