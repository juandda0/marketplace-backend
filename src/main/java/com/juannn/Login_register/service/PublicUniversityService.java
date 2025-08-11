package com.juannn.Login_register.service;

import com.juannn.Login_register.dto.campus.response.CampusResponse;
import com.juannn.Login_register.dto.university.response.UniversityResponse;
import com.juannn.Login_register.mapper.CampusMapper;
import com.juannn.Login_register.mapper.UniversityMapper;
import com.juannn.Login_register.model.uni.Campus;
import com.juannn.Login_register.repository.CampusRepository;
import com.juannn.Login_register.repository.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to handle business logic related to exposing
 * public data of universities and campuses.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublicUniversityService {

    private final UniversityRepository universityRepository;
    private final CampusRepository campusRepository;
    private final UniversityMapper universityMapper;
    private final CampusMapper campusMapper;

    /**
     * Retrieves all active universities.
     * @return List of DTOs representing active universities.
     */
    public List<UniversityResponse> findAllActiveUniversities() {
        return universityRepository.findByIsActiveTrue().stream()
                .map(universityMapper::toUniversityResponse)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all campuses for a specific and active university.
     * @param universityId The ID of the university.
     * @return List of DTOs representing campuses.
     */
    public List<CampusResponse> findCampusesByUniversity(Long universityId) {
        // First validate that the university exists and is active
        if (!universityRepository.existsByIdAndIsActiveTrue(universityId)) {
            throw new EntityNotFoundException("Active university not found with id: " + universityId);
        }
        List<Campus> campuses = campusRepository.findByUniversity_Id(universityId);
        return campuses.stream()
                .map(campusMapper::toCampusResponse)
                .collect(Collectors.toList());
    }
}