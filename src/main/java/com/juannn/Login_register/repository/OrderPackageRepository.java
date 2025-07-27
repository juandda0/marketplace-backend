package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.Order;
import com.juannn.Login_register.model.OrderPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderPackageRepository extends JpaRepository<Order, Long> {
    List<OrderPackage> findByOrderId(Long orderId);
    List<OrderPackage> findByStoreId(Long storeId);
}
