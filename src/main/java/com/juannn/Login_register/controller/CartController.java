package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.cart.response.CartResponse;
import com.juannn.Login_register.dto.cart.request.CartItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller to manage the shopping cart of the authenticated user.
 * Requires the user to be logged in.
 */
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    // Endpoint to get the contents of the current user's cart.
    @GetMapping
    public ResponseEntity<CartResponse> getCart() {
        // Logic to retrieve the cart associated with the authenticated user.
        CartResponse cart = new CartResponse(1L, List.of(), BigDecimal.ZERO); // Mock
        return ResponseEntity.ok(cart);
    }

    // Endpoint to add a new item (a product variant) to the cart.
    @PostMapping("/items")
    public ResponseEntity<CartResponse> addItemToCart(@RequestBody CartItemRequest itemRequest) {
        // Logic to add the item and return the updated cart.
        CartResponse updatedCart = new CartResponse(1L, List.of(), BigDecimal.TEN); // Mock
        return ResponseEntity.ok(updatedCart);
    }

    // Endpoint to update the quantity of an item in the cart.
    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> updateCartItem(
            @PathVariable Long itemId,
            @RequestParam int quantity
    ) {
        // Logic to update the quantity. If quantity is 0, the item is removed.
        CartResponse updatedCart = new CartResponse(1L, List.of(), BigDecimal.valueOf(20)); // Mock
        return ResponseEntity.ok(updatedCart);
    }

    // Endpoint to remove an item from the cart.
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long itemId) {
        // Logic to remove an item from the cart.
        return ResponseEntity.noContent().build();
    }
}
