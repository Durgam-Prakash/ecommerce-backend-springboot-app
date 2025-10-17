package com.ecommerce.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.CartDto;
import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.CartItem;
import com.ecommerce.backend.pojo.AddToCartData;
import com.ecommerce.backend.repository.CartItemRepository;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void addToCart(AddToCartData addToCartData) {
		
//		int userId = addToCartData.getUserId();
//
//	    
//	    boolean userExists = userRepository.existsById(userId);
//	    if (!userExists) {
//	        throw new IllegalArgumentException("User with ID " + userId + " does not exist.");
//	    }
		
		Optional<Cart> cartOptional = cartRepository.findByUserId(addToCartData.getUserId());
		
		Cart cart = new Cart();
		if(cartOptional.isEmpty()) {
			cart.setUserId(addToCartData.getUserId());
			cart = cartRepository.save(cart);
		}else {
			
			 cart = cartOptional.get();
			
		}
		System.out.println(cart);
		
		CartItem cartItem = new CartItem();
		cartItem.setCartId(cart.getCartId());
		cartItem.setProductId(addToCartData.getProductId());
		cartItem.setQuantity(addToCartData.getQuantity());
		cartItemRepository.save(cartItem);
	}

	@Transactional
	public List<CartDto> getCart(int userId) throws Exception {
		Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
		
		if(cartOptional.isEmpty()) {
			throw new Exception("No items in the cart");
		}
		
		Cart cart = cartOptional.get();
		List<Object[]> cartDataObjects = cartRepository.getCartData(cart.getCartId());
		System.out.println(cartDataObjects);
		
		List<CartDto> cartDataList = new ArrayList<>();
		
		for(Object[] row:cartDataObjects) {
			CartDto tempCartDto = new CartDto();
			tempCartDto.setCartItemId((Integer)row[0]);
			tempCartDto.setProductId((Integer)row[1]);
			tempCartDto.setQuantity((Integer)row[2]);
			tempCartDto.setTitle((String)row[3]);
			tempCartDto.setProductDescription((String)row[4]);
			tempCartDto.setPrice(Double.parseDouble(String.valueOf(row[5])));
			tempCartDto.setImages((String)row[6]);
			
			cartDataList.add(tempCartDto);
			
		}
		

		return cartDataList;
		
	}
}
