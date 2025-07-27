package com.juannn.Login_register.dto.cart.response;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(
        Long id,
        List<CartItemResponse> items,
        BigDecimal total
) {}
