package com.api.grocery.models;

import java.util.List;

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
public class ProductRequestVO {
	private String id;
	private String name;
	private List<Promotion> promotions;
	private Integer qty;
}
