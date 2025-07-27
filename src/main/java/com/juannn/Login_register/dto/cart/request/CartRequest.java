package com.juannn.Login_register.dto.cart.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CartRequest (
        @NotNull
        List<CartItemRequest> items
){

}
