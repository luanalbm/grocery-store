package com.api.grocery.service;

import java.util.List;

import com.api.grocery.dto.OrderDto;

public interface OrderService {

	List<OrderDto> findAll();

}
