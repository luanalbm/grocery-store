package com.api.grocery.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.grocery.dto.OrderDto;
import com.api.grocery.repository.OrderRepository;
import com.api.grocery.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository productRepository;
	List<OrderDto> currentlyListOrder = new ArrayList<>();

	@Autowired
	public OrderServiceImpl(OrderRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<OrderDto> findAll() {
		return currentlyListOrder;
	}
	
}
