package com.api.grocery.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Entity
public class AdditionalPromotionsRequestVO {
    @NotNull(message = "Client is mandatory")
	private Customer customer;
    @NotNull(message = "Product is mandatory")
    private List<ProductRequestVO> products;
    
	public AdditionalPromotionsRequestVO() {
		super();
		this.products = new ArrayList<ProductRequestVO>();
	}

}
