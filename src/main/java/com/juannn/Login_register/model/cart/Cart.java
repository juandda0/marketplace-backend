/**
 * Entity Cart: Represents a shopping cart for a user.
 * A cart is a container that temporarily holds items that a user intends to purchase.
 * The cart is associated with one user, and contains multiple items (cart items).
 */
package com.juannn.Login_register.model.cart;

import com.juannn.Login_register.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {

    /**
     * The unique identifier for the cart.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user who owns the cart.
     */
    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}