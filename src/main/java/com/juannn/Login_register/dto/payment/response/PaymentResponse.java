package com.juannn.Login_register.dto.payment.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponse(
        Long id,
        Long orderId,
        String method,
        String status,
        String referenceCode,
        BigDecimal amount,
        LocalDateTime paidAt
) {
}
