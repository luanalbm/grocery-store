package com.api.grocery.service;

import java.util.List;

import com.api.grocery.models.Order;

import jakarta.servlet.http.HttpSession;

public interface SessionService {

	void setCurrentListOrder(List<Order> currentListOrder);

	List<Order> getCurrentListOrder();

	HttpSession getSession();

}
