package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.store.response.StoreResponse;
import com.juannn.Login_register.model.user.User;
import com.juannn.Login_register.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling client-facing store operations.
 * Endpoints are automatically scoped to the authenticated user's campus.
 */
@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * Retrieves a list of all stores registered in the authenticated user's campus.
     * @param user The authenticated user, injected by Spring Security.
     * @return A list of {@link StoreResponse} objects.
     */
    @GetMapping
    public ResponseEntity<List<StoreResponse>> getStoresByCampus(@AuthenticationPrincipal User user) {
        List<StoreResponse> stores = storeService.findStoresByCampus(user);
        return ResponseEntity.ok(stores);
    }

    /**
     * Retrieves the details of a specific store, validating it belongs to the user's campus.
     * @param user The authenticated user.
     * @param storeId The ID of the store to retrieve.
     * @return A {@link StoreResponse} with the store details.
     */
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponse> getStoreById(
            @AuthenticationPrincipal User user,
            @PathVariable Long storeId) {
        StoreResponse store = storeService.findStoreByIdAndCampus(storeId, user);
        return ResponseEntity.ok(store);
    }
}