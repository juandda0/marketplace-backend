package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProductId(Long productId);
    List<Review> findByUserId(UUID userId);
}
