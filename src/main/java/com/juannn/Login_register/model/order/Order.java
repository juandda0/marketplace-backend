package com.juannn.Login_register.model.order;

import com.juannn.Login_register.model.uni.DeliveryPoint;
import com.juannn.Login_register.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity Order: Represents an order made by a user.
 * An order is a purchase made by a user, which can be composed of multiple items.
 * The order is associated with one user (buyer) and one delivery point.
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String orderCode; // ej: "UNIM-2024-A8C3-B1D9"

    /**
     * The user who made the purchase (buyer).
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    /**
     * The delivery point where the order will be delivered.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_point_id", nullable = false)
    private DeliveryPoint deliveryPoint;

    /**
     * The status of the order.
     * Can be {@link OrderStatus#PENDING_PAYMENT}, {@link OrderStatus#PROCESSING},
     * {@link OrderStatus#SHIPPED}, {@link OrderStatus#COMPLETED}, {@link OrderStatus#CANCELLED},
     * or {@link OrderStatus#REFUNDED}.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status; // Must be set in the service

    /**
     * The total amount of the order.
     * This is the sum of the prices of all the items in the order.
     */
    @NotNull
    @PositiveOrZero
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    /**
     * The timestamp when the order was created.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * The timestamp when the order was last updated.
     * This is updated when the status is changed.
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}