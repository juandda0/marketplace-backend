package com.juannn.Login_register.dto.university.response;

public record UniversityResponse(
        Long id,
        String name,
        String logoUrl,
        String subdomain,
        String emailDomain
) {
}
