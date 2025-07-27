package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.Payment;
import com.juannn.Login_register.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Review, Long> {
    Optional<Payment> findByOrderId(Long orderId);
}
