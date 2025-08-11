package com.juannn.Login_register.service.entrepreneur;
import com.juannn.Login_register.dto.store.request.StoreRequest;
import com.juannn.Login_register.dto.store.response.StoreResponse;
import com.juannn.Login_register.mapper.StoreMapper;
import com.juannn.Login_register.model.store.Store;
import com.juannn.Login_register.model.store.StoreStatus;
import com.juannn.Login_register.model.user.Role;
import com.juannn.Login_register.model.user.User;
import com.juannn.Login_register.repository.StoreRepository;
import com.juannn.Login_register.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Service class for handling store management operations for a seller.
 * This includes creating, updating, and retrieving the seller's own store.
 */
@Service
@RequiredArgsConstructor
public class StoreManagementService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final StoreMapper storeMapper;

    /**
     * Creates a new store for the authenticated user.
     * This operation is idempotent; a user can only create one store.
     * It also promotes the user's role from CLIENT to ENTREPRENEUR.
     *
     * @param seller The authenticated user who will own the store.
     * @param request The DTO containing the details for the new store.
     * @return A DTO representing the newly created store.
     * @throws IllegalStateException if the user already has a store.
     */
    @Transactional
    public StoreResponse createStore(User seller, StoreRequest request) {
        // --- Validation: Ensure the user doesn't already have a store ---
        if (storeRepository.existsBySeller_Id(seller.getId())) {
            throw new IllegalStateException("User already owns a store.");
        }

        // --- Entity Mapping & Association ---
        Store store = storeMapper.toStore(request);
        store.setSeller(seller);
        store.setCampus(seller.getCampus()); // Automatically associate with the seller's campus
        store.setStatus(StoreStatus.ACTIVE); // Set default status

        Store savedStore = storeRepository.save(store);

        // --- Promote User Role ---
        Set<Role> roles = new HashSet<>(seller.getRoles());
        roles.add(Role.ENTREPRENEUR);
        seller.setRoles(roles);
        userRepository.save(seller);

        return storeMapper.toStoreResponse(savedStore);
    }

    /**
     * Retrieves the store associated with the authenticated seller.
     *
     * @param seller The authenticated user.
     * @return A DTO representing the user's store.
     * @throws EntityNotFoundException if the user does not have a store.
     */
    @Transactional(readOnly = true)
    public StoreResponse getStore(User seller) {
        Store store = storeRepository.findBySeller_Id(seller.getId())
                .orElseThrow(() -> new EntityNotFoundException("No store found for the current user."));
        return storeMapper.toStoreResponse(store);
    }

    /**
     * Updates the details of the authenticated seller's store.
     *
     * @param seller The authenticated user.
     * @param request The DTO containing the updated store information.
     * @return A DTO representing the updated store.
     * @throws EntityNotFoundException if the user does not have a store to update.
     */
    @Transactional
    public StoreResponse updateStore(User seller, StoreRequest request) {
        Store existingStore = storeRepository.findBySeller_Id(seller.getId())
                .orElseThrow(() -> new EntityNotFoundException("No store found for the current user to update."));

        // --- Update store fields from the request ---
        existingStore.setName(request.name());
        existingStore.setDescription(request.description());
        existingStore.setLogoUrl(request.logoUrl());
        existingStore.setBannerUrl(request.bannerUrl());

        Store updatedStore = storeRepository.save(existingStore);

        return storeMapper.toStoreResponse(updatedStore);
    }
}