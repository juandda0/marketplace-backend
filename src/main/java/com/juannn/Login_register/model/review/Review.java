package com.juannn.Login_register.model.review;

import com.juannn.Login_register.model.product.Product;
import com.juannn.Login_register.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Entity Review: Represents a review for a product by a user.
 * <p>
 * A review contains the rating and comment left by the user for the product.
 * It also contains the response from the seller.
 * The review is associated with the user and product.
 */
@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
public class Review {

    /**
     * The unique identifier of the review.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who left the review.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The product being reviewed.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * The rating left by the user.
     * <p>
     * The rating is between 1 and 5.
     */
    @NotNull
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer rating;

    /**
     * The comment left by the user.
     */
    @Column(columnDefinition = "TEXT")
    private String comment;

    /**
     * The response from the seller.
     */
    @Column(name = "seller_response", columnDefinition = "TEXT")
    private String sellerResponse;

    /**
     * The date and time when the review was created.
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}