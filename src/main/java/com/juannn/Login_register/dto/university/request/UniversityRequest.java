package com.juannn.Login_register.dto.university.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UniversityRequest(
        @NotBlank @Size(max = 255) String name,
        @NotBlank @Size(max = 100) String emailDomain,
        @Size(max = 2048) String logoUrl,
        @NotBlank @Size(max = 50) String subdomain,
        @NotNull Boolean isActive,
        @Size(max = 2048) String termsUrl
) {
}
