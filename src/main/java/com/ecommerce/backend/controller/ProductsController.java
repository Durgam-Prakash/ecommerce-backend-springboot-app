package com.ecommerce.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.constant.ExceptionConstants;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.pojo.SearchProductsAPIData;
import com.ecommerce.backend.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/search")
	public ResponseEntity<?> searchProduct(@RequestBody SearchProductsAPIData searchProductsAPIData) throws Exception{
		
		List<Product> searchProduct = productService.searchProduct(searchProductsAPIData);
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE, "your search Products are :");
		response.put(ExceptionConstants.API_DATA, searchProduct);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	
	@GetMapping("/getById/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable int productId) throws Exception{
		Object productById = productService.getProductById(productId);
		
		Map<String, Object> response = new HashMap<>();
		response.put(ExceptionConstants.API_STATUS, ExceptionConstants.API_SUCCESS);
		response.put(ExceptionConstants.API_MESSAGE,  " The product id is : " + productId + " Details are :");
		response.put(ExceptionConstants.API_DATA, productById);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
