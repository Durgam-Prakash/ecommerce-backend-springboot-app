package com.ecommerce.backend.schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
public class CartScheduler {
	
	@Autowired
	private CartRepository cartRepository;
	
	
	
	
	
	@Transactional
	@Scheduled(cron = "0 * * * * ?")
	public void sendCartReminders() {
		System.out.println("Running sendCartReminders method ");
		List<Object[]> cartReminders = cartRepository.getCartReminders();
		
		for(Object[] row :cartReminders) {
			System.out.println(row[0]);
			
		}
		
	}

}
