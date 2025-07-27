package com.juannn.Login_register.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email format is not valid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotBlank(message = "Student ID is required")
        @Size(max = 50, message = "Student ID must be at most 50 characters")
        String studentId,

        @NotNull(message = "Campus ID is required")
        Long campusId,

        @Size(max = 20, message = "Phone number is too long")
        String phone
) {}
