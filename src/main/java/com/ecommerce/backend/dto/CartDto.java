package com.ecommerce.backend.dto;

import lombok.Data;

@Data
public class CartDto {
	
	private int cartItemId;
	private int productId;
	private int quantity;
	private String title;
	private String productDescription;
	private Double price;
	private String images;

}
