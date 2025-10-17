package com.ecommerce.backend.pojo;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddToCartData {
	
	@Positive(message = "user id should not be zero")
	private int userId;
	
	@Positive(message = "product id should not be zero")
	private int productId;
	
	@Positive(message = "quantity  should  be greater than 0")
	private int quantity;
	

}
