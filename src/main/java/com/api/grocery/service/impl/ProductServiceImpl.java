package com.api.grocery.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import com.api.grocery.controller.configuration.ApiProperties;
import com.api.grocery.models.Product;
import com.api.grocery.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductServiceImpl implements ProductService{
	
	ApiProperties apiProperties;
	
	@Autowired
	public ProductServiceImpl(ApiProperties apiProperties) {
		this.apiProperties = apiProperties;
	}
	
	private RestClient restClient = RestClient.create();

	@Override
	public List<Product> findAll() throws Exception {
		try {
			ResponseEntity<String> result = restClient.get() 
					.uri(apiProperties.getProductUrl()) 
					.retrieve()
					.toEntity(String.class);
			
			ObjectMapper objectMapper = new ObjectMapper();
			return Arrays.asList(objectMapper.readValue(result.toString(), Product[].class));			
		} catch (Exception e) {
			throw new Exception("Error by consulting products:" + e.getMessage());
		}
	}

}
