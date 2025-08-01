package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findBySeller_Id(UUID sellerId);
    List<Store> findByCampus_Id(Long campusId);
}
