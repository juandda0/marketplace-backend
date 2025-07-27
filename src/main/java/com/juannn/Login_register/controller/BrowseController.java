package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.product.ProductResponse;
import com.juannn.Login_register.dto.store.response.StoreResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for clients to browse products and stores.
 * All endpoints here are publicly accessible (no authentication required)
 * to allow anyone to view the products.
 */
@RestController
@RequestMapping("/api/v1/browse")
public class BrowseController {

    // Endpoint to retrieve a paginated list of products, with optional filters.
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getProducts(
            @RequestParam(required = false) Long campusId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String searchTerm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        // Service logic to search and filter products.
        // A mock list is returned here for demonstration purposes.
        List<ProductResponse> products = List.of(
                new ProductResponse(1L, "Sample Product", "Description...", "Sample Store", 1L, "Category", List.of(), List.of())
        );
        return ResponseEntity.ok(products);
    }

    // Endpoint to retrieve the details of a specific product by its ID.
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        // Service logic to find a product by its ID.
        ProductResponse product = new ProductResponse(productId, "Sample Product", "Description...", "Sample Store", 1L, "Category", List.of(), List.of());
        return ResponseEntity.ok(product);
    }

    // Endpoint to retrieve the details of a specific store.
    @GetMapping("/stores/{storeId}")
    public ResponseEntity<StoreResponse> getStoreById(@PathVariable Long storeId) {
        // Service logic to find a store by its ID.
        StoreResponse store = new StoreResponse(storeId, "Sample Store", "Description...", null, null, 4.5);
        return ResponseEntity.ok(store);
    }
}
