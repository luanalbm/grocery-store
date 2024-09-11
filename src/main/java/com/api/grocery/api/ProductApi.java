
package com.api.grocery.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.api.grocery.models.Product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product", description = "The Product API")
public interface ProductApi {

    @Operation(summary = "Find all products", description = "Fetches all product entities from the wiremock data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    @GetMapping
    ResponseEntity<List<Product>> findAll() throws Exception;

    @Operation(summary = "Find product by ID", description = "Fetches a product entity by its ID from the wiremock data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping(path = "/{productId}")
    ResponseEntity<Product> find(@PathVariable String productId) throws Exception;
}
