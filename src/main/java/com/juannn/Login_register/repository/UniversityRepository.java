package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.uni.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findByIsActiveTrue();

    boolean existsByIdAndIsActiveTrue(Long universityId);
}
