package com.ecommerce.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApiPrefixConfig implements WebMvcConfigurer{
	
	public void  configurePathMatch(PathMatchConfigurer configure) {
		configure.addPathPrefix("api",c->c.isAnnotationPresent(RestController.class));
	}

}
