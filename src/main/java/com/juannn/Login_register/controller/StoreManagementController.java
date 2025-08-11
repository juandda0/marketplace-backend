package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.store.request.StoreRequest;
import com.juannn.Login_register.dto.store.response.StoreResponse;
import com.juannn.Login_register.model.user.User;
import com.juannn.Login_register.service.entrepreneur.StoreManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for sellers to manage their own store.
 * Provides endpoints for store creation, retrieval, and updates.
 * All operations require the user to be authenticated.
 */
@RestController
@RequestMapping("/api/v1/store-management")
@RequiredArgsConstructor
public class StoreManagementController {

    private final StoreManagementService storeManagementService;

    /**
     * Creates a new store for the authenticated user.
     * This endpoint should be used once by a user to become a seller.
     * The user must have at least the 'CLIENT' role.
     *
     * @param user The authenticated user principal.
     * @param request The request body containing store details.
     * @return A response entity with the created store's data and a 201 status.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('CLIENT')") // Ensures only users who are not yet sellers can create a store
    public ResponseEntity<StoreResponse> createStore(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody StoreRequest request) {
        StoreResponse newStore = storeManagementService.createStore(user, request);
        return new ResponseEntity<>(newStore, HttpStatus.CREATED);
    }

    /**
     * Retrieves the details of the authenticated seller's store.
     * The user must have the 'ENTREPRENEUR' role to access this.
     *
     * @param user The authenticated user principal.
     * @return A response entity with the store's data.
     */
    @GetMapping("/my-store")
    @PreAuthorize("hasAuthority('ENTREPRENEUR')")
    public ResponseEntity<StoreResponse> getMyStore(@AuthenticationPrincipal User user) {
        StoreResponse store = storeManagementService.getStore(user);
        return ResponseEntity.ok(store);
    }

    /**
     * Updates the details of the authenticated seller's store.
     * The user must have the 'ENTREPRENEUR' role.
     *
     * @param user The authenticated user principal.
     * @param request The request body containing the updated store details.
     * @return A response entity with the updated store's data.
     */
    @PutMapping("/my-store")
    @PreAuthorize("hasAuthority('ENTREPRENEUR')")
    public ResponseEntity<StoreResponse> updateMyStore(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody StoreRequest request) {
        StoreResponse updatedStore = storeManagementService.updateStore(user, request);
        return ResponseEntity.ok(updatedStore);
    }
}