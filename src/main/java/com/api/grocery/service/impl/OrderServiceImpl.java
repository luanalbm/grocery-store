package com.api.grocery.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.grocery.models.AdditionalPromotionsRequestVO;
import com.api.grocery.models.CustomerItemRequestVO;
import com.api.grocery.models.ItemRequestVO;
import com.api.grocery.models.Order;
import com.api.grocery.models.Product;
import com.api.grocery.models.Promotion;
import com.api.grocery.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	ProductServiceImpl productService;
	SessionServiceImpl sessionService;

	@Autowired
	public OrderServiceImpl(ProductServiceImpl productService, SessionServiceImpl sessionService) {
		super();
		this.productService = productService;
		this.sessionService = sessionService;
	}

	@Override
	public List<Order> findAll() {
	    return sessionService.getCurrentListOrder();
	}

	@Override
	public void addItems(CustomerItemRequestVO customerItemReq) {
		List<Order> currentListOrder = sessionService.getCurrentListOrder();
		
		Order newOrder = new Order(customerItemReq.getCustomer(), setInfoProducts(customerItemReq.getItems()));
		Order existingOrder = currentListOrder.stream()
	            .filter(order -> order.getCustomer().getId().equals(newOrder.getCustomer().getId()))
	            .findFirst()
	            .orElseGet(() -> {
	                currentListOrder.add(newOrder);
	                return null;
	            });

		if(existingOrder != null) {			
			newOrder.getProducts().forEach(newProduct -> {	    	
				Product existingProduct = existingOrder.getProducts().stream()
						.filter(oldProduct -> oldProduct.getId().equals(newProduct.getId()))
						.findFirst()
						.orElseGet(() -> {
							existingOrder.getProducts().add(newProduct);
							return newProduct;
						});
				
				existingProduct.setQty(existingProduct.getQty() + newProduct.getQty());
			});
		}
	    sessionService.setCurrentListOrder(currentListOrder);
	}
	
	@Override
	public void deleteOrder(String customerId) {
		List<Order> currentListOrder = sessionService.getCurrentListOrder();
		currentListOrder.removeIf(order -> order.getCustomer().getId().equals(customerId));
	}
	
	@Override
	public Order findByCustomer(String customerId) {
		List<Order> currentListOrder = sessionService.getCurrentListOrder();
		return currentListOrder.stream().filter(order -> order.getCustomer().getId().equals(customerId)).findFirst()
				.orElse(null);
	}
	
	public List<Product> setInfoProducts(List<ItemRequestVO> itemsRequest) {
		return itemsRequest.stream()
				.map(itemReq -> {
					try {
						Product infoProduct = productService.find(itemReq.getId());
						infoProduct.setQty(itemReq.getQty());
						infoProduct.getPromotions().forEach(promotion -> {
			                promotion.setPrice(promotion.getPrice() != null ? promotion.getPrice().divide(new BigDecimal(100)) : null);
			            });
						infoProduct.setPrice(infoProduct.getPrice().divide(new BigDecimal(100)));
						return infoProduct;
					} catch (Exception e) {
						throw new RuntimeException("Product not found: " + e.getMessage());
					}
				})
				.collect(Collectors.toList());
    }

	@Override
	public Order getCustomerSavings(String customerId) {
		List<Order> currentListOrder = sessionService.getCurrentListOrder();
		return currentListOrder.stream()
				.filter(order -> order.getCustomer().getId().equals(customerId))
				.map(order -> {
					Order updatedOrder = order;
					updatedOrder.applyPromotion();
					return updatedOrder;
				})
				.findFirst()
				.orElse(null);
	}

	@Override
	public void applyAdditionalPromotions(List<AdditionalPromotionsRequestVO> additionalPromotions) {
		List<Order> currentListOrder = sessionService.getCurrentListOrder();
		if (currentListOrder == null || currentListOrder.isEmpty()) {
			throw new RuntimeException("No orders found.");
		}
		additionalPromotions.forEach(additionalPromotion -> {
			Order existingOrder = currentListOrder.stream()
					.filter(order -> order.getCustomer().getId().equals(additionalPromotion.getCustomer().getId()))
					.findFirst()
					.orElse(null);
			
			if(existingOrder != null) {			
				additionalPromotion.getProducts().forEach(newProduct -> {	    	
					newProduct.getPromotions().stream().map(promotion -> {
                        promotion.setPrice(promotion.getPrice() != null ? promotion.getPrice().divide(new BigDecimal(100)) : null);
                        return promotion;
                    }).collect(Collectors.toList());
					
					existingOrder.getProducts().stream()
							.filter(oldProduct -> oldProduct.getId().equals(newProduct.getId()))
							.findFirst()
							.map(oldProduct -> {
								oldProduct.setPromotions(additionalPromotion != null ? 
                                        Arrays.asList(Stream.concat(oldProduct.getPromotions().stream(), newProduct.getPromotions().stream()).toArray(Promotion[]::new)) 
                                        : oldProduct.getPromotions());
                                return oldProduct;
                            })
							.orElse(null);					
				});
				
				sessionService.setCurrentListOrder(currentListOrder);
			}			        
		});
	}
	
}
