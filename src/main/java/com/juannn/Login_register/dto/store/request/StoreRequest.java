package com.juannn.Login_register.dto.store.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StoreRequest (
        @NotBlank @Size(max = 100) String name, // must be unique
        @NotNull Long campusId,
        @Size(max = 5000) String description,
        @Size(max = 2048) String logoUrl,
        @Size(max = 2048) String bannerUrl
) {
}
