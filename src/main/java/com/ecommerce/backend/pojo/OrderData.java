package com.ecommerce.backend.pojo;

import lombok.Data;

@Data
public class OrderData {
	
	private int userId;
	private int cartId;
	private int addressId;

}
