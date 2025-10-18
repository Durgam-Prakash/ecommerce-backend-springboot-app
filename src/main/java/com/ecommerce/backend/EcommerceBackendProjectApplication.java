package com.ecommerce.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EcommerceBackendProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceBackendProjectApplication.class, args);
	}

}
