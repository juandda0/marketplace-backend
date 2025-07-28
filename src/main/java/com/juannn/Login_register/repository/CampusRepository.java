package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.uni.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Long> {
    List<Campus> findByUniversityId(Long universityId);
}
