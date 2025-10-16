package com.ecommerce.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.entity.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Transactional
	@Procedure(procedureName = "sp_search_products_data")
	List<Product> searchProducts(@Param("inp_search_word") String searchWord);


}
