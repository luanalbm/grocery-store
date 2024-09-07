package com.api.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.grocery.dto.OrderDto;
import com.api.grocery.service.OrderService;

@RestController
@RequestMapping(path = "order")
public class OrderController {
	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping
	public List<OrderDto> findAll() {
		return orderService.findAll();
	}
	
}
