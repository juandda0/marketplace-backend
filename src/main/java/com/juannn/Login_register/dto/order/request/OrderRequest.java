package com.juannn.Login_register.dto.order.request;

import jakarta.validation.constraints.NotNull;

public record OrderRequest(
        @NotNull Long deliveryPointId
) {}