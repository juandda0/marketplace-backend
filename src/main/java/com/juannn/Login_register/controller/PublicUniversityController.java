package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.campus.response.CampusResponse;
import com.juannn.Login_register.dto.university.response.UniversityResponse;
import com.juannn.Login_register.service.PublicUniversityService; // <-- DEPENDENCIA ACTUALIZADA
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for providing public information about universities and campuses,
 * primarily for the user registration process.
 */
@RestController
@RequestMapping("/api/v1/public/universities")
@RequiredArgsConstructor
public class PublicUniversityController {

    private final PublicUniversityService publicUniversityService;

    /**
     * Retrieves a list of all active universities.
     * This is used to populate the first dropdown in the registration form.
     * @return A list of active universities.
     */
    @GetMapping()
    public ResponseEntity<List<UniversityResponse>> getActiveUniversities() {
        List<UniversityResponse> activeUniversities = publicUniversityService.findAllActiveUniversities();
        return ResponseEntity.ok(activeUniversities);
    }

    /**
     * Retrieves a list of campuses for a given active university.
     * This is used to populate the dependent campus dropdown after a university is selected.
     * @param universityId The ID of the selected university.
     * @return A list of campuses for the given university.
     */
    @GetMapping("/{universityId}/campuses")
    public ResponseEntity<List<CampusResponse>> getCampusesByUniversity(@PathVariable Long universityId) {
        List<CampusResponse> activeCampuses = publicUniversityService.findCampusesByUniversity(universityId);
        return ResponseEntity.ok(activeCampuses);
    }
}