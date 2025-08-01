package com.juannn.Login_register.model.product;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entity ProductCategory: Represents a category of products in the store.
 * A product category is a grouping of related products, such as "Electronics" or "Clothing".
 * Categories are used to organize products in the store and to help users find related products.
 */
@Entity
@Table(name = "product_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

    /**
     * The unique identifier for the product category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the product category.
     * This is the name that is displayed to users in the store.
     * It must be unique and is limited to 100 characters.
     */
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String name;
}