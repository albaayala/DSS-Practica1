package com.alba.dss.practica1.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.alba.dss.practica1.model.Product;


@Service
@SessionScope		// cada sesion de http tiene su instancia de servicio.
public class CartService {
	
	private final List<Product> cart = new ArrayList<>();
	
	public void addProduct(Product product) {
		
		cart.add(product);
	}
	
	public void removeProduct(Product product) {
		this.removeProductById(product.getId());
	}
	
	public void removeProductById(Long id) {
		cart.removeIf(p -> p.getId()== id);
	}
		
	public List<Product> getCart(){
		return cart;
	}
	
//	public double getTotalPrice() {
//		double total = 0.0;
//		
//		if(cart!=null) 
//			for (Product p : cart) 
//				total += p.getPrice();
//		
//		
//		return total;
//	}
//	
//	public void clearCart() {
//		cart.clear();
//	}
	
	
}
