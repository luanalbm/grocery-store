package com.api.grocery.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.grocery.models.Product;
import com.api.grocery.service.ProductService;

@RestController
@RequestMapping(path = "product")
public class ProductController {
	private ProductService productService;
	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public List<Product> findAll() {
		try {
			return productService.findAll();
		} catch (Exception e) {
			logger.error("Error on method findAll(): " + e.getMessage());
			return new ArrayList<Product>();
		}
	}
	
}
