package com.api.grocery.models;

import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.annotation.JsonInclude;

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
public class RestResponseJsonVO {
	private Integer status;
	private HttpHeaders headers;
	private Object jsonBody;
	private String message;
}

