package com.api.grocery.service;

import java.util.List;

import com.api.grocery.models.AdditionalPromotionsRequestVO;
import com.api.grocery.models.CustomerItemRequestVO;
import com.api.grocery.models.Order;

public interface OrderService {

	List<Order> findAll();

	void addItems(CustomerItemRequestVO customerItemReq);

	void deleteOrder(String customerId);

	Order findByCustomer(String customerId);

	Order getCustomerSavings(String customerId);

	void applyAdditionalPromotions(List<AdditionalPromotionsRequestVO> additionalPromotions);


}
