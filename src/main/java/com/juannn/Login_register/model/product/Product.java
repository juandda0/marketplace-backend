package com.juannn.Login_register.model.product;

import com.juannn.Login_register.model.store.Store;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Entity Product: Represents a product in the store.
 * A product is associated with a single store and a single category.
 * It contains the name, description, and price of the product, as well as a list of images.
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    /**
     * The unique identifier of the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the product.
     */
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String name;

    /**
     * The description of the product.
     */
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * The category of the product.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    /**
     * The store of the product.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    /**
     * The list of images of the product.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images;
}