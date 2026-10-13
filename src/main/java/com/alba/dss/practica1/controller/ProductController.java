package com.alba.dss.practica1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alba.dss.practica1.model.Product;
import com.alba.dss.practica1.service.ProductService;



@Controller
public class ProductController {

	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(value = "/admin")
	public String adminPage(Model model){
		model.addAttribute("products", productService.getAllProducts());
		return "admin";
	}
	
	@RequestMapping(value = "/products")
	public String getProducts(Model model){
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String addProduct(Model model){
		model.addAttribute("products", productService.getAllProducts()); // El fichero admin.html pide una lista de productos
		return "admin";
	}
	
	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String saveNewProduct(Product product){
		
		productService.saveProduct(product);
		
		return "redirect:/products/add";
		
	}
	
	@RequestMapping(value="/products/edit/{id}", method = RequestMethod.GET)
	public String editProduct(@PathVariable Long id, Model model) {
		
		//Product product = productService.getProductById(id);
		model.addAttribute("product", productService.getProductById(id));
		
		return "product_form";	
	}
	
	@RequestMapping(value="/products/edit/{id}", method = RequestMethod.POST)
	public String saveProduct(@PathVariable Long id, Product product,
			@RequestHeader(value="Referer", required=false)String referer) {
		
		productService.saveProduct(product);
		
		if(referer != null && referer.contains("/products/add")) {
			return "redirect:/products/add";
		}
		
		return "redirect:/products";
	}

	
//	@RequestMapping(value = "/products/save", method = RequestMethod.POST)
//	public String saveProduct(Product product){
//		productService.saveProduct(product);
//		return "redit:/products";
//	}

	@RequestMapping(value="/products/delete/{id}",  method = RequestMethod.POST)
	public String deleteProduct(@PathVariable Long id,
			@RequestHeader(value="Referer", required=false)String referer) {
		Product product = productService.getProductById(id);
		
		productService.deleteProduct(product);
		
		if(referer != null && referer.contains("/products/add")) {
			return "redirect:/products/add";
		}
		
		return "redirect:/products";
		
	}
}
