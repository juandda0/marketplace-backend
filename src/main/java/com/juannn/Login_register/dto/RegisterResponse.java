package com.juannn.Login_register.dto;

import java.util.UUID;

public record RegisterResponse(
        UUID id,
        String username,
        String email
) {
}
