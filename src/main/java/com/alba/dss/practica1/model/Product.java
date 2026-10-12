package com.alba.dss.practica1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.NoArgsConstructor;


@Entity
//@Getter
//@Setter
//@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private double price;
	
	public Product() { }
	
	public Long getId() {
        return id;
    }

	public double getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(Long id) {
        this.id = id;
    }
	
	public void setName(String name) {
        this.name = name;
    }
	
	public void setPrice(double price) {
        this.price = price;
    }
	
}