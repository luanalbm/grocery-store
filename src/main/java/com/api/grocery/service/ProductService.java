package com.api.grocery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.grocery.models.Product;

@Service
public interface ProductService {

	List<Product> findAll() throws Exception;

}
