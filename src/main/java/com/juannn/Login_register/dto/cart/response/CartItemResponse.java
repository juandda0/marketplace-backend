package com.juannn.Login_register.dto.cart.response;

import java.math.BigDecimal;

public record CartItemResponse(
        Long id,
        Long variantId,
        String productName,
        BigDecimal price,
        int quantity,
        String imageUrl
) {}
