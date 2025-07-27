package com.juannn.Login_register.dto.cart.request;

public record CartItemRequest(
        Long variantId,
        int quantity
) {}