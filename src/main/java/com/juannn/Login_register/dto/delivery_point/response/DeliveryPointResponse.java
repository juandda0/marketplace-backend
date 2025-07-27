package com.juannn.Login_register.dto.delivery_point.response;

import com.juannn.Login_register.dto.campus.response.CampusResponse;

public record DeliveryPointResponse(
        Long id,
        String name,
        String description,
        CampusResponse campus
) {
}
