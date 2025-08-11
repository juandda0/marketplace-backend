package com.juannn.Login_register.dto.auth.response;

import com.juannn.Login_register.model.user.User;
import lombok.Builder;

import java.util.UUID;

public record UserProfileResponse(
        UUID id,
        String name,
        String lastName,
        String email,
        String studentId,
        String phone,
        String University,
        String campusName
) {}
