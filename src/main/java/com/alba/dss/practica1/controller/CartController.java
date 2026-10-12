package com.alba.dss.practica1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alba.dss.practica1.model.Product;
import com.alba.dss.practica1.service.CartService;
import com.alba.dss.practica1.service.ProductService;


@Controller
public class CartController {
	
private final CartService cartService;
private final ProductService productService;
	
	public CartController(CartService cartService, ProductService productService) {
		this.cartService = cartService;
		this.productService = productService;
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String getCart(Model model){
		model.addAttribute("cartItems", cartService.getCart());
		return "cart";
	}
	
	@RequestMapping(value="/cart/add/{id}", method = RequestMethod.POST)
	public String addProduct(@PathVariable Long id, Model model) {
		
		Product product = productService.getProductById(id);
		
		if (product != null) {
			cartService.addProduct(product);
		}

	     return "redirect:/products";
	}
	
	@RequestMapping(value="/cart/remove/{id}", method = RequestMethod.POST)
	public String removeProduct(@PathVariable Long id, Model model) {
		
		//Product product = productService.getProductById(id);
		
		cartService.removeProductById(id);
		
		return "redirect:/cart";
	}
	
	
	

}
