package com.alba.dss.practica1.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alba.dss.practica1.repository.ProductRepo;
import com.alba.dss.practica1.model.Product;

@Service
public class DatabaseExportService {

	@Autowired
	private ProductRepo productRepo;
	
	public DatabaseExportService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	public byte[] exportDatabaseToSql() {
		
		// Obtenemos todos los products
		List<Product> products = productRepo.findAll();
		
		 StringBuilder sql = new StringBuilder();
		
		 // Añadir si se puede la creacion de tablas si no estan creadas
		 
		// Por cada producto se crea un insert
		
		 for (Product product : products) {
			 
			 sql.append("INSERT INTO product (id, name, price) VALUES (");
		
			 sql.append(product.getId()).append(", ");
			 
			 sql.append("'").append(product.getName()).append("', ");
			 
			 sql.append(product.getPrice()).append(");\n");		 
		 
		 }
		
		 return sql.toString().getBytes(StandardCharsets.UTF_8);
		
	}
	
}
