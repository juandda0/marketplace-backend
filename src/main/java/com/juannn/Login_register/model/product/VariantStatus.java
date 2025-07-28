package com.juannn.Login_register.model.product;

/**
 * Enum representing the status of a product variant in the university marketplace.
 * This status helps control whether the variant is available for purchase, temporarily
 * out of stock, or no longer offered.
 */
public enum VariantStatus {
    AVAILABLE,      // Available for sale.
    OUT_OF_STOCK,   // out of stock, but visible.
    DISCONTINUED    // The selle no longer ofers this variant.
}
