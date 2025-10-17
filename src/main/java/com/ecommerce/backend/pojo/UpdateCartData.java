package com.ecommerce.backend.pojo;

import lombok.Data;

@Data
public class UpdateCartData {
	
	
	private int cartId;
	private int productId;
	private int quantity;

}
