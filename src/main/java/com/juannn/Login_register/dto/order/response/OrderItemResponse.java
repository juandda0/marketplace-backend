package com.juannn.Login_register.dto.order.response;

import com.juannn.Login_register.dto.product.ProductVariantResponse;

import java.math.BigDecimal;

public record OrderItemResponse(
        Long id,
        Integer quantity,
        BigDecimal price,
        ProductVariantResponse variant
) {}
