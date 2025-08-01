package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyer_IdOrderByCreatedAtDesc(UUID buyerId);
}
