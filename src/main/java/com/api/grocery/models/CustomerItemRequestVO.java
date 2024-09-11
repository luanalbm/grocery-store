package com.api.grocery.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CustomerItemRequestVO {
    @NotNull(message = "Customer is mandatory")
	private Customer customer;
    @NotNull(message = "Product is mandatory")
	private List<ItemRequestVO> items;
}
