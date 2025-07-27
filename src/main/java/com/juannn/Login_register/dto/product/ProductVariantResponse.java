package com.juannn.Login_register.dto.product;

import java.math.BigDecimal;
import java.util.Map;

// DTO para las variantes de un producto.
public record ProductVariantResponse(
        Long id,
        BigDecimal price,
        Integer stock,
        Map<String, String> attributes
) {}