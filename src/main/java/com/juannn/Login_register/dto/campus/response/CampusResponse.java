package com.juannn.Login_register.dto.campus.response;

import com.juannn.Login_register.dto.university.response.UniversityResponse;

public record CampusResponse(
        Long id,
        String name,
        UniversityResponse university
) {
}
