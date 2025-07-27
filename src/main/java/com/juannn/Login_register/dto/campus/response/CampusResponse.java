package com.juannn.Login_register.dto.campus.response;

public record CampusResponse(
        Long id,
        String name,
        UniversityResponse university
) {
}
