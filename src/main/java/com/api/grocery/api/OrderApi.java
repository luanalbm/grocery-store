
package com.api.grocery.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.grocery.models.AdditionalPromotionsRequestVO;
import com.api.grocery.models.CustomerItemRequestVO;
import com.api.grocery.models.Order;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Order", description = "The Order API")
public interface OrderApi {

    @Operation(summary = "Add items to order", description = "Adds items to an order -> Items can be added in any order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(path = "/add-items")
    ResponseEntity<?> addItems(@Valid @RequestBody CustomerItemRequestVO customerItemReq);

    @Operation(summary = "Get customer savings", description = "Fetches the customer savings -> Promotions should be applied whenever applicable and the store owner wants to display the customer's savings.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping(path = "/{customerId}")
    ResponseEntity<Order> getCustomerSavings(@PathVariable String customerId);

    @Operation(summary = "Apply additional promotions", description = "Applies additional promotions to an order -> Additional price or promotion types might be introduced later.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(path = "/apply-additional-promotions")
    ResponseEntity<String> applyAdditionalPromotions(@Valid @RequestBody List<AdditionalPromotionsRequestVO> additionalPromotions);
}
