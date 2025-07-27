package com.juannn.Login_register.dto.order.response;

import com.juannn.Login_register.dto.store.response.StoreResponse;

import java.util.List;

public record OrderPackageResponse(
        Long id,
        String status,
        StoreResponse store,
        List<OrderItemResponse> items
) {}