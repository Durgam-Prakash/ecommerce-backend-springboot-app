package com.ecommerce.backend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecommerce.backend.constant.ExceptionConstants;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errors.put(error.getField(), error.getDefaultMessage());
			
			});
		
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_MESSAGE, "Unable to Process Your Request..");
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_FAILED);
		response.put(ExceptionConstants.API_ERROR, errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception ex){
		
		Map<String, String> response = new HashMap<>();
		response.put(ExceptionConstants.API_RESULTS, ExceptionConstants.API_FAILED);
		response.put(ExceptionConstants.API_DATA, "unable to process your request..");
		response.put(ExceptionConstants.API_MESSAGE, ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		
	}

}
