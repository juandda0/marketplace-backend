package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.auth.request.AdminCreationRequest;
import com.juannn.Login_register.dto.auth.response.UserResponse;
import com.juannn.Login_register.dto.campus.request.CampusRequest;
import com.juannn.Login_register.dto.campus.response.CampusResponse;
import com.juannn.Login_register.dto.delivery_point.request.DeliveryPointRequest;
import com.juannn.Login_register.dto.delivery_point.response.DeliveryPointResponse;
import com.juannn.Login_register.dto.university.request.UniversityRequest;
import com.juannn.Login_register.dto.university.response.UniversityResponse;
import com.juannn.Login_register.service.PlatformAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for platform-level administration operations.
 * All endpoints here require the 'SUPER_ADMIN' role.
 */
@RestController
@RequestMapping("/api/v1/admin/platform")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('SUPER_ADMIN')")
public class PlatformAdminController {

    private final PlatformAdminService platformAdminService;

    /**
     * Creates a new university.
     * @param universityRequest Request body with university details.
     * @return Created university response.
     */
    @PostMapping("/universities")
    public ResponseEntity<UniversityResponse> createUniversity(@Valid @RequestBody UniversityRequest universityRequest) {
        UniversityResponse newUniversity = platformAdminService.createUniversity(universityRequest);
        return new ResponseEntity<>(newUniversity, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all universities.
     * @return List of universities.
     */
    @GetMapping("/universities")
    public ResponseEntity<List<UniversityResponse>> getAllUniversities() {
        return ResponseEntity.ok(platformAdminService.findAllUniversities());
    }

    /**
     * Updates the active status of a university.
     * @param universityId University ID to update.
     * @param isActive New active status.
     * @return No content response.
     */
    @PatchMapping("/universities/{universityId}/status")
    public ResponseEntity<Void> updateUniversityStatus(@PathVariable Long universityId, @RequestParam boolean isActive) {
        platformAdminService.setUniversityStatus(universityId, isActive);
        return ResponseEntity.noContent().build();
    }

    /**
     * Creates a new administrator for a specific university.
     * @param universityId ID of the target university.
     * @param adminRequest Admin user data.
     * @return Created admin response.
     */
    @PostMapping("/universities/{universityId}/admins")
    public ResponseEntity<UserResponse> createUniversityAdmin(
            @PathVariable Long universityId,
            @Valid @RequestBody AdminCreationRequest adminRequest) {
        UserResponse newAdmin = platformAdminService.createUniversityAdmin(universityId, adminRequest);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    /**
     * Creates a new campus.
     * @param campusRequest Campus data.
     * @return Created campus response.
     */
    @PostMapping("/campuses")
    public ResponseEntity<CampusResponse> createCampus(@Valid @RequestBody CampusRequest campusRequest) {
        CampusResponse newCampus = platformAdminService.createCampus(campusRequest);
        return new ResponseEntity<>(newCampus, HttpStatus.CREATED);
    }

    /**
     * Creates a new delivery point.
     * @param deliveryPointRequest Delivery point data.
     * @return Created delivery point response.
     */
    @PostMapping("/delivery-points")
    public ResponseEntity<DeliveryPointResponse> createDeliveryPoint(@Valid @RequestBody DeliveryPointRequest deliveryPointRequest) {
        DeliveryPointResponse newDeliveryPoint = platformAdminService.createDeliveryPoint(deliveryPointRequest);
        return new ResponseEntity<>(newDeliveryPoint, HttpStatus.CREATED);
    }

    /**
     * Updates the active status of a delivery point.
     * @param pointId Delivery point ID to update.
     * @param isActive New active status.
     * @return No content response.
     */
    @PatchMapping("/delivery-points/{pointId}/status")
    public ResponseEntity<Void> updateDeliveryPointStatus(@PathVariable Long pointId, @RequestParam boolean isActive) {
        platformAdminService.setDeliveryPointStatus(pointId, isActive);
        return ResponseEntity.noContent().build();
    }
}
