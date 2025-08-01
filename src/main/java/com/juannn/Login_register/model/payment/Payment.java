package com.juannn.Login_register.model.payment;

import com.juannn.Login_register.model.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Entity Payment: Represents a transaction of a payment for an order.
 * <p>
 * When a user makes a payment for an order, a Payment entity is created, which is associated with the order.
 * The payment entity contains the amount of the payment and the method used for the payment.
 * The status of the payment is initially PENDING and is updated to SUCCESSFUL or FAILED after the payment has been processed.
 */

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    /**
     * Unique identifier of the payment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The order that the payment is for.
     */
    @NotNull
    @OneToOne(fetch = FetchType.LAZY) // An order can have only one payment
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;

    /**
     * The amount of the payment.
     */
    @NotNull
    @PositiveOrZero
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    /**
     * The method used for the payment.
     */
    @Size(max = 50)
    private String method;

    /**
     * The status of the payment.
     * <p>
     * The status is initially PENDING and is updated to SUCCESSFUL or FAILED after the payment has been processed.
     */
    @Enumerated
    @Size(max = 50)
    private PaymentStatus status;

    /**
     * The transaction ID of the payment.
     */
    @Size(max = 255)
    @Column(name = "transaction_id")
    private String transactionId;

    /**
     * The date and time when the payment was created.
     */
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * The date and time when the payment was processed.
     */
    @Column(name = "processed_at")
    private LocalDateTime processedAt;
}