package com.juannn.Login_register.dto.delivery_point.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DeliveryPointRequest(
        @NotBlank @Size(max = 150) String name,
        @NotBlank @Size(max = 500) String description,
        @NotNull Long campusId
) {
}
