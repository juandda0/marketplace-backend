package com.juannn.Login_register.model.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entity ProductImage: Represents an image associated with a product.
 * A product image is a URL that points to an image of the product.
 * The image can be marked as primary, meaning it is the main image for the product.
 */
@Entity
@Table(name = "product_images")
@Data
@NoArgsConstructor
public class ProductImage {

    /**
     * Unique identifier for the image.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * URL of the image.
     * This is the URL where the image is hosted.
     */
    @NotBlank
    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    /**
     * Flag to indicate if this image is the primary image for the product.
     * If true, this image is the main image for the product.
     */
    @Column(name = "is_primary")
    private boolean isPrimary = false;

    /**
     * The product that this image belongs to.
     * This is the product that the image is associated with.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}