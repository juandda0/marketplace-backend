package com.juannn.Login_register.dto.campus.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CampusRequest(
        @NotNull(message = "University ID is required") Long universityId,
        @NotBlank @Size(max = 255) String name
){
}
