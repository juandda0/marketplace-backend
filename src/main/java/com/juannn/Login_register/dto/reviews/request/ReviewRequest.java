package com.juannn.Login_register.dto.reviews.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReviewRequest (
        @NotNull @Min(1) @Max(5) Integer rating,
        String comment
){
}
