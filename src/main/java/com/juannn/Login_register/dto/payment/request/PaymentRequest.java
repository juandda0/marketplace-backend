package com.juannn.Login_register.dto.payment.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PaymentRequest(
        @NotNull Long orderId,
        @NotBlank @Size(max = 50) String method
) {
}
