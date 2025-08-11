package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.product.ProductResponse;
import com.juannn.Login_register.model.user.User;
import com.juannn.Login_register.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling client-facing product operations.
 * All endpoints are automatically scoped to the authenticated user's campus.
 */
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Retrieves a paginated list of products available in the authenticated user's campus.
     * @param user The authenticated user, injected by Spring Security to determine the campus.
     * @param pageable Pagination information (e.g., ?page=0&size=10).
     * @return A {@link Page} of {@link ProductResponse} objects available in the user's campus.
     */
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProductsByCampus(
            @AuthenticationPrincipal User user,
            Pageable pageable) {
        Page<ProductResponse> products = productService.findProductsByCampus(user, pageable);
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves the details of a specific product, ensuring it belongs to the user's campus.
     * @param user The authenticated user.
     * @param productId The ID of the product to retrieve.
     * @return A {@link ProductResponse} with the product details.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(
            @AuthenticationPrincipal User user,
            @PathVariable Long productId) {
        ProductResponse product = productService.findProductByIdAndCampus(productId, user);
        return ResponseEntity.ok(product);
    }

    /**
     * Searches for products by a query term within the authenticated user's campus.
     * @param user The authenticated user.
     * @param query The search term.
     * @return A list of {@link ProductResponse} objects matching the query in the user's campus.
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProductsInCampus(
            @AuthenticationPrincipal User user,
            @RequestParam("query") String query) {
        List<ProductResponse> products = productService.searchProductsInCampus(query, user);
        return ResponseEntity.ok(products);
    }
}