package com.juannn.Login_register.mapper;

import com.juannn.Login_register.dto.cart.response.CartItemResponse;
import com.juannn.Login_register.dto.cart.response.CartResponse;
import com.juannn.Login_register.model.cart.Cart;
import com.juannn.Login_register.model.cart.CartItem;
import com.juannn.Login_register.model.product.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper {

    @Mapping(source = "items", target = "items")
    CartResponse toCartResponse(Cart cart);

    @Mapping(source = "variant.id", target = "variantId")
    @Mapping(source = "variant.product.name", target = "productName")
    @Mapping(source = "variant.price", target = "price")
    @Mapping(source = "variant.product.images", target = "imageUrl", qualifiedByName = "firstImageUrl")
    CartItemResponse toCartItemResponse(CartItem cartItem);

    @Named("firstImageUrl")
    default String firstImageUrl(List<ProductImage> images) {
        if (images == null || images.isEmpty()) {
            return null; // O una URL de imagen por defecto
        }
        return images.stream()
                .filter(ProductImage::isPrimary)
                .map(ProductImage::getImageUrl)
                .findFirst()
                .orElse(images.get(0).getImageUrl());
    }
}