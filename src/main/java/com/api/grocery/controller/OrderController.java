package com.api.grocery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.grocery.api.OrderApi;
import com.api.grocery.models.AdditionalPromotionsRequestVO;
import com.api.grocery.models.CustomerItemRequestVO;
import com.api.grocery.models.Order;
import com.api.grocery.service.OrderService;
import com.api.grocery.service.SessionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "order-customer")
public class OrderController implements OrderApi {
	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService, SessionService sessionService) {
		this.orderService = orderService;
	}
	
	@PostMapping(path = "/add-items")
	public ResponseEntity<?> addItems(@Valid @RequestBody CustomerItemRequestVO customerItemReq) {
		orderService.addItems(customerItemReq);
        return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/{customerId}")
	public ResponseEntity<Order> getCustomerSavings(@PathVariable String customerId) {
		Order order = orderService.getCustomerSavings(customerId);
		if (order == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PostMapping(path = "/apply-additional-promotions")
	public ResponseEntity<String> applyAdditionalPromotions(@Valid @RequestBody List<AdditionalPromotionsRequestVO> additionalPromotions) {
		orderService.applyAdditionalPromotions(additionalPromotions);
		return new ResponseEntity<String>("Additional promotions applied, please check customer savings to confer.", HttpStatus.OK);
	}
}
