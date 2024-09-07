package com.api.grocery.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.api.grocery.enums.OrderStateEnum;
import com.api.grocery.models.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String customerId;
	private OrderStateEnum orderState;
	private BigDecimal subTotalPrice;
	private BigDecimal promotions;
	private BigDecimal totalPrice;
	private List<Product> products;
}
