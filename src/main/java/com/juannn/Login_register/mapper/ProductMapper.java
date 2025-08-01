package com.juannn.Login_register.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juannn.Login_register.dto.product.ProductResponse;
import com.juannn.Login_register.dto.product.ProductVariantResponse;
import com.juannn.Login_register.model.product.Product;
import com.juannn.Login_register.model.product.ProductImage;
import com.juannn.Login_register.model.product.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    @Mapping(source = "store.name", target = "storeName")
    @Mapping(source = "store.id", target = "storeId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "images", target = "imageUrls", qualifiedByName = "imagesToUrls")
    ProductResponse toProductResponse(Product product);

    @Mapping(source = "attributes", target = "attributes", qualifiedByName = "jsonToMap")
    ProductVariantResponse toProductVariantResponse(ProductVariant variant);

    @Named("imagesToUrls")
    default List<String> imagesToUrls(List<ProductImage> images) {
        if (images == null) {
            return Collections.emptyList();
        }
        return images.stream()
                .map(ProductImage::getImageUrl)
                .collect(Collectors.toList());
    }

    @Named("jsonToMap")
    default Map<String, String> jsonToMap(String json) {
        if (json == null || json.isEmpty()) {
            return Collections.emptyMap();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
        } catch (JsonProcessingException e) {
            // Manejar la excepción, por ejemplo, loguearla y devolver un mapa vacío.
            return Collections.emptyMap();
        }
    }
}