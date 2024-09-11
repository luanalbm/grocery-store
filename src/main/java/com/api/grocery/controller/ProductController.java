package com.api.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.grocery.api.ProductApi;
import com.api.grocery.models.Product;
import com.api.grocery.service.ProductService;

@RestController
@RequestMapping(path = "product")
public class ProductController implements ProductApi {
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;

	}
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll() throws Exception {
		List<Product> products = productService.findAll();
		if (products == null || products.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{productId}")
	public ResponseEntity<Product> find(@PathVariable String productId) throws Exception {
		Product product = productService.find(productId);
		if (product == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
}
