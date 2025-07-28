package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByName(String name);
}
