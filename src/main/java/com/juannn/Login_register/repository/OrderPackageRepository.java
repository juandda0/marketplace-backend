package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.order.Order;
import com.juannn.Login_register.model.order.OrderPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderPackageRepository extends JpaRepository<OrderPackage, Long> {
    List<OrderPackage> findByOrder_Id(Long orderId);
    List<OrderPackage> findByStore_Id(Long storeId);
}
