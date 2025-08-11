package com.juannn.Login_register.service;

import com.juannn.Login_register.dto.auth.request.AdminCreationRequest;
import com.juannn.Login_register.dto.auth.response.UserResponse;
import com.juannn.Login_register.dto.campus.request.CampusRequest;
import com.juannn.Login_register.dto.campus.response.CampusResponse;
import com.juannn.Login_register.dto.delivery_point.request.DeliveryPointRequest;
import com.juannn.Login_register.dto.delivery_point.response.DeliveryPointResponse;
import com.juannn.Login_register.dto.university.request.UniversityRequest;
import com.juannn.Login_register.dto.university.response.UniversityResponse;
import com.juannn.Login_register.mapper.*;
import com.juannn.Login_register.model.uni.Campus;
import com.juannn.Login_register.model.uni.DeliveryPoint;
import com.juannn.Login_register.model.uni.University;
import com.juannn.Login_register.model.user.Role;
import com.juannn.Login_register.model.user.User;
import com.juannn.Login_register.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service that encapsulates the business logic for SUPER_ADMIN-level operations.
 * By default, methods are read-only for better performance and transaction safety.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlatformAdminService {

    private final UniversityRepository universityRepository;
    private final CampusRepository campusRepository;
    private final DeliveryPointRepository deliveryPointRepository;
    private final UserRepository userRepository;

    private final UniversityMapper universityMapper;
    private final CampusMapper campusMapper;
    private final DeliveryPointMapper deliveryPointMapper;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    /**
     * Creates and persists a new university.
     * @param request Data for the new university.
     * @return UniversityResponse with created university details.
     */
    @Transactional
    public UniversityResponse createUniversity(UniversityRequest request) {
        University university = universityMapper.toUniversity(request);

        String domain = request.emailDomain().trim().toLowerCase();

        if (domain.contains("@")) {
            domain = domain.substring(domain.indexOf("@") + 1);
        }

        university.setEmailDomain(domain);
        University savedUniversity = universityRepository.save(university);
        return universityMapper.toUniversityResponse(savedUniversity);
    }

    /**
     * Retrieves all universities in the system.
     * @return List of UniversityResponse.
     */
    public List<UniversityResponse> findAllUniversities() {
        return universityRepository.findAll().stream()
                .map(universityMapper::toUniversityResponse)
                .collect(Collectors.toList());
    }

    /**
     * Updates the 'active' status of a university.
     * @param universityId Target university ID.
     * @param isActive New status (true = active, false = inactive).
     */
    @Transactional
    public void setUniversityStatus(Long universityId, boolean isActive) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + universityId));
        university.setActive(isActive);
        universityRepository.save(university);
    }

    /**
     * Creates a new UNIVERSITY_ADMIN user and links it to a university.
     * @param universityId ID of the target university.
     * @param request Admin creation data.
     * @return UserResponse with admin details.
     */
    @Transactional
    public UserResponse createUniversityAdmin(Long universityId, AdminCreationRequest request) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + universityId));

        // You should validate here that the email is not already in use.

        User adminUser = User.builder()
                .name(request.name())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .roles(Set.of(Role.UNIVERSITY_ADMIN))
                .managedUniversity(university)
                .isActive(true)
                .isVerified(true)
                .build();

        User savedAdmin = userRepository.save(adminUser);
        return new UserResponse(savedAdmin.getId(), savedAdmin.getName(), savedAdmin.getEmail(), "UNIVERSITY_ADMIN");
    }

    /**
     * Creates a new campus and links it to a university.
     * @param request Campus creation data.
     * @return CampusResponse with saved campus details.
     */
    @Transactional
    public CampusResponse createCampus(CampusRequest request) {
        University university = universityRepository.findById(request.universityId())
                .orElseThrow(() -> new EntityNotFoundException("University not found with id: " + request.universityId()));

        Campus campus = campusMapper.toCampus(request);
        campus.setUniversity(university);
        Campus savedCampus = campusRepository.save(campus);
        return campusMapper.toCampusResponse(savedCampus);
    }

    /**
     * Creates a new delivery point and links it to a campus.
     * @param request Delivery point creation data.
     * @return DeliveryPointResponse with saved delivery point details.
     */
    @Transactional
    public DeliveryPointResponse createDeliveryPoint(DeliveryPointRequest request) {
        Campus campus = campusRepository.findById(request.campusId())
                .orElseThrow(() -> new EntityNotFoundException("Campus not found with id: " + request.campusId()));

        DeliveryPoint deliveryPoint = deliveryPointMapper.toDeliveryPoint(request);
        deliveryPoint.setCampus(campus);
        DeliveryPoint savedPoint = deliveryPointRepository.save(deliveryPoint);
        return deliveryPointMapper.toDeliveryPointResponse(savedPoint);
    }

    /**
     * Updates the 'active' status of a delivery point.
     * @param pointId ID of the delivery point.
     * @param isActive New status (true = active, false = inactive).
     */
    @Transactional
    public void setDeliveryPointStatus(Long pointId, boolean isActive) {
        DeliveryPoint point = deliveryPointRepository.findById(pointId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery Point not found with id: " + pointId));
        point.setActive(isActive);
        deliveryPointRepository.save(point);
    }
}
