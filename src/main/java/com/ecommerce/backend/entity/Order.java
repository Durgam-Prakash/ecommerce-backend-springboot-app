package com.ecommerce.backend.entity;

import java.time.LocalDateTime;

import com.ecommerce.backend.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private int userId;
	private Double totalAmount;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	private int billingAddressId;
	private int shippingAddressId;
	
	
	private LocalDateTime createdOn = LocalDateTime.now();
	private LocalDateTime updatedOn = LocalDateTime.now();
	

}
