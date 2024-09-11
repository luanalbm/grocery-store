package com.api.grocery.service;

import java.util.List;

import com.api.grocery.models.Product;

public interface ProductService {

	List<Product> findAll() throws Exception;

	Product find(String productId) throws Exception;

}
