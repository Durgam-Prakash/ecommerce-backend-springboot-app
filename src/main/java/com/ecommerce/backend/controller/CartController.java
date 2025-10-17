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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.constant.ExceptionConstants;
import com.ecommerce.backend.dto.CartDto;
import com.ecommerce.backend.entity.CartItem;
import com.ecommerce.backend.pojo.AddToCartData;
import com.ecommerce.backend.pojo.UpdateCartData;
import com.ecommerce.backend.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addToCart(@Valid @RequestBody AddToCartData addToCartData){
		
		cartService.addToCart(addToCartData);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE,  addToCartData.getQuantity() + " products are added into your cart");
		response.put(ExceptionConstants.API_DATA, addToCartData);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
				
	}
	
	
	@GetMapping("/view/{userId}")
	public ResponseEntity<?> getCart(@PathVariable int userId) throws Exception{
		
		List<CartDto> cart = cartService.getCart(userId);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "Your cart items details");
		response.put(ExceptionConstants.API_DATA, cart);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping("/update/{cartItemId}")
	public ResponseEntity<?> updateCart(@PathVariable int cartItemId,@RequestBody UpdateCartData updateCartData) throws Exception{
		CartItem updateCartItems = cartService.updateCartItems(cartItemId, updateCartData);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE,  cartItemId +" product is updated into your cart");
		response.put(ExceptionConstants.API_DATA, updateCartItems);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<?> deleteCart(@PathVariable int cartItemId) throws Exception{
		cartService.deleteCart(cartItemId);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE,  cartItemId +" product is removed from your cart");
		response.put(ExceptionConstants.API_DATA, "");
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
