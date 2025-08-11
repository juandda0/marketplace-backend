package com.juannn.Login_register.service;

import com.juannn.Login_register.dto.store.response.StoreResponse;
import com.juannn.Login_register.mapper.StoreMapper;
import com.juannn.Login_register.model.store.Store;
import com.juannn.Login_register.model.user.User;
import com.juannn.Login_register.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for store-related business logic.
 * Provides methods to find stores, automatically filtering by the user's campus.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    /**
     * Finds all stores located in the authenticated user's campus.
     * @param user The authenticated user.
     * @return A list of store DTOs.
     */
    public List<StoreResponse> findStoresByCampus(User user) {
        Long campusId = user.getCampus().getId();
        List<Store> stores = storeRepository.findByCampus_Id(campusId);
        return stores.stream()
                .map(storeMapper::toStoreResponse)
                .collect(Collectors.toList());
    }

    /**
     * Finds a single store by its ID, ensuring it is located in the user's campus.
     * @param storeId The ID of the store to find.
     * @param user The authenticated user.
     * @return The corresponding store DTO.
     * @throws EntityNotFoundException if the store doesn't exist or is not in the user's campus.
     */
    public StoreResponse findStoreByIdAndCampus(Long storeId, User user) {
        Long campusId = user.getCampus().getId();
        Store store = storeRepository.findByIdAndCampus_Id(storeId, campusId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found or not available in this campus."));
        return storeMapper.toStoreResponse(store);
    }
}