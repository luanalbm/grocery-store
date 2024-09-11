package com.api.grocery.models;

import java.math.BigDecimal;

import com.api.grocery.enums.PromotionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Promotion {
	private String id;
	private PromotionType type;
	@JsonProperty("required_qty")
	private Integer requiredQty;	
	@JsonProperty("free_qty")
	private Integer freeQty;
	private BigDecimal price;
	private BigDecimal amount;
	
}
