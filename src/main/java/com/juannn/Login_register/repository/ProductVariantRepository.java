package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByProduct_Id(Long productId);
    Optional<ProductVariant> findBySku(String sku);
}
