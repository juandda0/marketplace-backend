package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderPackageId(Long orderPackageId);
}
