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
import com.ecommerce.backend.entity.Order;
import com.ecommerce.backend.pojo.OrderData;
import com.ecommerce.backend.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@Valid @RequestBody OrderData orderData) throws Exception{
		Order order = orderService.createOrder(orderData);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "Your order is created succesfully");
		response.put(ExceptionConstants.API_DATA,order);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
