package com.api.grocery.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.api.grocery.models.Product;
import com.api.grocery.service.ProductService;
import com.api.grocery.service.WireMockService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductServiceImpl implements ProductService {
	
	private WireMockService wireMockService;
	
	@Autowired
	public ProductServiceImpl() {
		super();
		wireMockService = new WireMockServiceImpl();
	}

	private RestClient restClient = RestClient.create();
	private final String localhost = "http://localhost:";

	@Override
	public List<Product> findAll() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return Arrays.asList(objectMapper.readValue(callProductsEndpoint(null), Product[].class));			
	}
	
	@Override
	public Product find(String productId) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(callProductsEndpoint(productId), Product.class);			
	}
	
	private String callProductsEndpoint(String pathVariable) throws Exception {
		try {
			wireMockService.initWireMockServer();
			String uri = localhost  + wireMockService.getPort() + "/products";
			if (pathVariable != null) uri += "/" + pathVariable;
			
			ResponseEntity<String> result = restClient.get() 
					.uri(uri) 
					.retrieve()
					.toEntity(String.class);
			
			return result.getBody().toString();			
		} catch (Exception e) {
			throw new Exception("Error by consulting products:" + e.getMessage());
		}
	}
	
}
