package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStore_Id(Long storeId);
    List<Product> findByCategory_Id(Long categoryId);
    Page<Product> findByStore_Campus_Id(Long campusId, Pageable pageable);
    Optional<Product> findByIdAndStore_Campus_Id(Long productId, Long campusId);
    List<Product> searchByNameContainingAndStore_Campus_Id(String query, Long campusId);
}
