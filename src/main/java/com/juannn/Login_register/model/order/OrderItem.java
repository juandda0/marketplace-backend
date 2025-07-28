package com.juannn.Login_register.model.order;

import com.juannn.Login_register.model.product.ProductVariant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Entity OrderItem: Represents an item in an order package.
 * An order item is associated with one order package and one product variant.
 * It contains the quantity of the item and the price of the item in the order.
 */
@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem {

    /**
     * Unique identifier for the order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Order package that this item belongs to.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private OrderPackage orderPackage;

    /**
     * Product variant that this item represents.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false)
    private ProductVariant variant;

    /**
     * Quantity of the item in the order.
     */
    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Price of the item in the order.
     */
    @NotNull
    @PositiveOrZero
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}