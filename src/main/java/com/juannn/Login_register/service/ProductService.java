package com.juannn.Login_register.service;

import com.juannn.Login_register.dto.product.ProductResponse;
import com.juannn.Login_register.mapper.ProductMapper;
import com.juannn.Login_register.model.product.Product;
import com.juannn.Login_register.model.user.User;
import com.juannn.Login_register.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for product-related business logic.
 * Encapsulates logic for retrieving and searching products, scoped to a user's campus.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Finds a paginated list of products belonging to the user's campus.
     * @param user The authenticated user, used to identify the campus.
     * @param pageable Pagination configuration.
     * @return A page of product DTOs.
     */
    public Page<ProductResponse> findProductsByCampus(User user, Pageable pageable) {
        if (user.getCampus() == null) {
            throw new IllegalStateException("User is not associated with a campus.");
        }
        Long campusId = user.getCampus().getId();
        Page<Product> productPage = productRepository.findByStore_Campus_Id(campusId, pageable);
        return productPage.map(productMapper::toProductResponse);
    }

    /**
     * Finds a single product by its ID, ensuring it is available in the user's campus.
     * @param productId The ID of the product to find.
     * @param user The authenticated user.
     * @return The corresponding product DTO.
     * @throws EntityNotFoundException if the product doesn't exist or is not in the user's campus.
     */
    public ProductResponse findProductByIdAndCampus(Long productId, User user) {
        Long campusId = user.getCampus().getId();
        Product product = productRepository.findByIdAndStore_Campus_Id(productId, campusId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found or not available in this campus."));
        return productMapper.toProductResponse(product);
    }

    /**
     * Searches for products by name within a specific campus.
     * @param query The search query string.
     * @param user The authenticated user.
     * @return A list of product DTOs matching the search criteria.
     */
    public List<ProductResponse> searchProductsInCampus(String query, User user) {
        Long campusId = user.getCampus().getId();
        List<Product> products = productRepository.searchByNameContainingAndStore_Campus_Id(query, campusId);
        return products.stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}