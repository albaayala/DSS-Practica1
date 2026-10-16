package com.alba.dss.practica1.config;

import com.alba.dss.practica1.model.Product;
import com.alba.dss.practica1.repository.ProductRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

	@Bean
	public CommandLineRunner initData(ProductRepo productRepository) {
		return args -> {
			Product p1 = new Product();
			p1.setName("Portatil");
			p1.setPrice(1000);
			
			Product p2 = new Product();
			p2.setName("Ratón");
			p2.setPrice(25);
			
			
			productRepository.save(p1);
			productRepository.save(p2);
		};
	
	}
	
}
