package com.juannn.Login_register.dto.reviews.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReviewResponse(
        Long id,
        String username,
        Long productId,
        Integer rating,
        String comment,
        LocalDateTime createdAt
) {
}
