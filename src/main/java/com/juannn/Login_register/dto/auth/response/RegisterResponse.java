package com.juannn.Login_register.dto.auth.response;

import java.util.UUID;

public record RegisterResponse(
        UUID id,
        String username,
        String email
) {
}
