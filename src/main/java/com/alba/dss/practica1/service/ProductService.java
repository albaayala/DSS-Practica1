package com.alba.dss.practica1.service;

import org.springframework.stereotype.Service;

import com.alba.dss.practica1.repository.ProductRepo;
import com.alba.dss.practica1.model.Product;

import java.util.Comparator;
import java.util.List;

// Implementar:
// 	- getAllProduct
//	- getProductById
//	- saveProduct
// 	- deleteProduct
@Service
public class ProductService {
	
	private final ProductRepo productRepo;
	
	public ProductService(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	
	public List <Product>  getAllProducts(){
		return productRepo.findAll();
	}
	
	public Product getProductById(Long id){
		return productRepo.findById(id).orElse(null);
	}
	
	public void saveProduct(Product product) {
		productRepo.save(product);
	}
	
	public void deleteProduct(Product product) {
		productRepo.delete(product);
	}
	
	// Plus
	public List<Product> getAllProductsOrder(boolean ascendente ) {
		// Obtenemos al lista de productos
		List <Product> res = productRepo.findAll();
		
		// la ordenamos de forma descendente
		res.sort(Comparator.comparingDouble(Product::getPrice));
		
		// Si es ascendete le damos la vuelta
		if(ascendente) {
			res = res.reversed();
		}
		
		return res;
	}
	
	public List<Product> getPartProduct(double min, double max){
		
		// Filtramos la lista,para los que esten entre [min, max]
		List<Product> res = productRepo.findAll().stream().filter(product->product.getPrice()>=min && product.getPrice()<=max).toList();
		
		return res;
	}
	
}
