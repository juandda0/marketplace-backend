package com.juannn.Login_register.dto.auth.response;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String role
) {}
