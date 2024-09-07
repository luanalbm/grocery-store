package com.api.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.grocery.models.Product;

public interface OrderRepository extends JpaRepository<Product, Long>{
	
	
}
