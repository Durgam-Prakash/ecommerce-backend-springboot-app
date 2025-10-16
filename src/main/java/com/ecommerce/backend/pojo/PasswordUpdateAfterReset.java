package com.ecommerce.backend.pojo;

import com.ecommerce.backend.constant.AuthConstants;
import com.ecommerce.backend.constant.RegexConstatnts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordUpdateAfterReset {
	
	@NotBlank(message = AuthConstants.ERROR_EMAI_ID_REQUIRED)
	@Pattern(regexp = RegexConstatnts.EMAIL_REGEX,message = AuthConstants.ERROR_EMAIL_INVALID)
	private String emailId;
	
	@NotBlank(message = AuthConstants.ERROR_PASSWORD_REQUIRED)
	@Size(min = 6,message = AuthConstants.ERROR_PASSWORD_MIN_6_CHARACTERS)
	private String password;
	
	@NotNull(message = AuthConstants.ERROR_OTP_IS_REQUIRED)
	private String otp;
	

}
