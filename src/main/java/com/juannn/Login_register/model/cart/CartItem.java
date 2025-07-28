package com.juannn.Login_register.model.cart;

import com.juannn.Login_register.model.product.ProductVariant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Entity CartItem: Represents an item in a shopping cart.
 * A cart item is associated with a single cart and a single product variant.
 */
@Entity
@Table(name = "cart_items")
@Data
@NoArgsConstructor
public class CartItem {

    /**
     * Unique identifier for the cart item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The cart that this item belongs to.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    /**
     * The product variant associated with this cart item.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false)
    private ProductVariant variant;

    /**
     * The quantity of the product variant in the cart.
     */
    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer quantity;
}