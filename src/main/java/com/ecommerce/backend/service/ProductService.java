package com.ecommerce.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.pojo.SearchProductsAPIData;
import com.ecommerce.backend.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public List<Product> searchProduct(SearchProductsAPIData searchProductsAPIData) throws Exception {
		List<Product> searchProducts = productRepository.searchProducts(searchProductsAPIData.getSearchWord());
		if(searchProducts.isEmpty()) {
			throw new Exception( searchProductsAPIData.getSearchWord() + " --> " + " product is not avaliable.." );
		}
		
		return searchProducts;
		
	}

}
