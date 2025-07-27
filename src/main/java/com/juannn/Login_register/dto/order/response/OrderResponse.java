package com.juannn.Login_register.dto.order.response;

import com.juannn.Login_register.dto.delivery_point.response.DeliveryPointResponse;
import com.juannn.Login_register.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        String orderCode,
        Long buyerId,
        DeliveryPointResponse deliveryPoint,
        OrderStatus status,
        BigDecimal total,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

