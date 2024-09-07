package com.api.grocery.models;

import java.math.BigDecimal;

import com.api.grocery.enums.OrderStateEnum;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Order {
	private String customerId;
	private OrderStateEnum orderState;
	private BigDecimal subTotalPrice;
	private BigDecimal promotions;
	private BigDecimal totalPrice;
}
