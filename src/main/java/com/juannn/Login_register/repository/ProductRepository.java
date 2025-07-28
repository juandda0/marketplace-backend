package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStoreId(Long storeId);
    List<Product> findByCategoryId(Long categoryId);
}
