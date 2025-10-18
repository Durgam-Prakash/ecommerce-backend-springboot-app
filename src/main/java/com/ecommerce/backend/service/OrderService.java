package com.ecommerce.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.CartItem;
import com.ecommerce.backend.entity.Order;
import com.ecommerce.backend.entity.OrderItem;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.enums.OrderStatus;
import com.ecommerce.backend.pojo.OrderData;
import com.ecommerce.backend.repository.CartItemRepository;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.OrderItemRepository;
import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.repository.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public Order createOrder(OrderData orderData) throws Exception {
		Optional<Cart> cartOptional = cartRepository.findByUserIdAndCartId(orderData.getCartId(),orderData.getUserId());
		
		if(cartOptional.isEmpty()) {
			throw new Exception("cart id invalid");
		}
		
		Cart cart = cartOptional.get();
		List<CartItem> cartItems = cartItemRepository.findBycartId(orderData.getCartId());
		System.out.println(cartItems.size());
		
		
		Double totalPrice =0.0; 
		
		for(CartItem cartItem :cartItems) {
			System.out.println(cartItem.getProductId());
			
			Optional<Product> optionalProduct = productRepository.findById(cartItem.getProductId());
			Product product = optionalProduct.get();
			totalPrice = totalPrice +(product.getPrice()*cartItem.getQuantity());
			
		}
		System.out.println(totalPrice);
		
		Order order = new Order();
		order.setUserId(orderData.getUserId());
		order.setTotalAmount(totalPrice);
		order.setBillingAddressId(orderData.getAddressId());
		order.setShippingAddressId(orderData.getAddressId());
		order.setOrderStatus(OrderStatus.PENDING);
		order =orderRepository.save(order);
		
		for(CartItem cartItem : cartItems) {
			
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(order.getOrderId());
			orderItem.setProductId(cartItem.getProductId());
			Optional<Product> optionalProduct = productRepository.findById(cartItem.getProductId());
			Product product =optionalProduct.get();
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItemRepository.save(orderItem);
		}
		
		return order;
		
	}
	
	

}
