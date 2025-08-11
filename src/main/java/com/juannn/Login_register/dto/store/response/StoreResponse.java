package com.juannn.Login_register.dto.store.response;

import com.juannn.Login_register.dto.campus.response.CampusResponse;
import com.juannn.Login_register.model.store.StoreStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record StoreResponse(
        Long id,
        UUID sellerId,
        String name,
        String description,
        String logoUrl,
        String bannerUrl,
        CampusResponse campus,
        double averageRating,
        int ratingCount,
        int totalSales,
        boolean offersShipping,
        StoreStatus status,
        LocalDateTime createdAt
) {}
