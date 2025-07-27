package com.juannn.Login_register.dto.product;

import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        String description,
        String storeName,
        Long storeId,
        String categoryName,
        List<String> imageUrls,
        List<ProductVariantResponse> variants
) {}
