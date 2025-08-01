package com.juannn.Login_register.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminCreationRequest(
        @NotBlank String name,
        @NotBlank String lastName,
        @Email String email,
        @NotBlank @Size(min = 8) String password
) {}
