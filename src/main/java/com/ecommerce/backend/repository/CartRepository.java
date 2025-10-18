package com.ecommerce.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend.entity.Cart;

import jakarta.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	Optional<Cart> findByUserId(int userId);
	
	@Transactional
	@Procedure(procedureName = "sp_cart_view_based_on_cart_id")
	List<Object[]> getCartData(@Param("in_cart_id")int cartId); 
	
	
	
	@org.springframework.transaction.annotation.Transactional
	@Procedure(procedureName = "sp_cart_reminders")
	List<Object[]> getCartReminders();

}
