package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.order.response.OrderResponse;
import com.juannn.Login_register.dto.order.request.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller for creating and retrieving orders.
 * Requires authentication.
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

//    // Endpoint to create a new order from the user's cart.
//    @PostMapping
//    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
//        // Service logic to create the order.
//        OrderResponse createdOrder = new OrderResponse(1L, "PENDING", BigDecimal.TEN, "Central Library", List.of()); // Mock
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
//    }

//    // Endpoint to get the authenticated user's order history.
//    @GetMapping
//    public ResponseEntity<List<OrderResponse>> getOrderHistory() {
//        // Service logic to retrieve the user's orders.
//        List<OrderResponse> orders = List.of(
//                new OrderResponse(1L, "COMPLETED", BigDecimal.TEN, "Central Library", List.of()) // Mock
//        );
//        return ResponseEntity.ok(orders);
//    }

    // Endpoint to get the details of a specific order.
//    @GetMapping("/{orderId}")
//    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable Long orderId) {
//        return ;
//    }
}
