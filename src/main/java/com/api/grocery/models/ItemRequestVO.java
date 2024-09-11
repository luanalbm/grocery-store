package com.api.grocery.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ItemRequestVO {
    @NotBlank(message = "Id is mandatory")
	private String id;
	private String name;
	private Integer qty;
}
