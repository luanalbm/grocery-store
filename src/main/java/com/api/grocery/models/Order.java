package com.api.grocery.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.api.grocery.enums.PromotionType;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {
	private Customer customer;
	private BigDecimal totalProductsPrice;
	private BigDecimal discountByProductPromotions;
	private BigDecimal totalLiquid;
	private List<Product> products;
    
	public Order() {
		super();
		this.products = new ArrayList<Product>();
	}
	
	public Order(Customer customer, List<Product> products) {
		super();
		this.customer = customer;
		this.products = products;
	}
	
	public void applyPromotion() {
		this.discountByProductPromotions = BigDecimal.ZERO;
		this.totalProductsPrice = BigDecimal.ZERO;
		this.products.forEach(product -> {
			BigDecimal totalPriceByProduct = product.getPrice().multiply(BigDecimal.valueOf(product.getQty()));
			if (product.getPromotions() != null && !product.getPromotions().isEmpty()) {
				product.getPromotions() .forEach(promotion -> {
					if (promotion.getType().equals(PromotionType.QTY_BASED_PRICE_OVERRIDE)
							&& product.getQty() >= promotion.getRequiredQty()
							&& product.getPrice().compareTo(promotion.getPrice()) > 0) {
						BigDecimal totalNewPricePromotionByProduct = promotion.getPrice().multiply(BigDecimal.valueOf(product.getQty()));
						this.discountByProductPromotions = this.discountByProductPromotions.add(totalPriceByProduct.subtract(totalNewPricePromotionByProduct).setScale(2, RoundingMode.HALF_UP));
					} else if (promotion.getType().equals(PromotionType.BUY_X_GET_Y_FREE) && product.getQty() >= promotion.getRequiredQty()) {
						Integer eligibleProductsQty = product.getQty() / promotion.getRequiredQty();
						Integer freeProductsQty = eligibleProductsQty * promotion.getFreeQty();
						this.discountByProductPromotions = this.discountByProductPromotions.add(product.getPrice().multiply(BigDecimal.valueOf(freeProductsQty)).setScale(2, RoundingMode.HALF_UP));
					} else if (promotion.getType().equals(PromotionType.FLAT_PERCENT)) {
						BigDecimal discountRatio = promotion.getAmount().divide(BigDecimal.valueOf(100));
						this.discountByProductPromotions = this.discountByProductPromotions.add(totalPriceByProduct.multiply(discountRatio).setScale(2, RoundingMode.HALF_UP));
					}
				});
			}
			
			this.totalProductsPrice = this.totalProductsPrice.add(totalPriceByProduct.setScale(2, RoundingMode.HALF_UP));
		});
		this.totalLiquid = this.totalProductsPrice.subtract(this.discountByProductPromotions).setScale(2, RoundingMode.HALF_UP);
	}

}
