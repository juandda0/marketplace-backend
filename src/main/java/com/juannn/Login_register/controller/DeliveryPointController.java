package com.juannn.Login_register.controller;

import com.juannn.Login_register.dto.delivery_point.response.DeliveryPointResponse;
import com.juannn.Login_register.model.user.User;
import com.juannn.Login_register.service.DeliveryPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller to retrieve available delivery points for a user.
 * This is essential for the checkout process.
 */
@RestController
@RequestMapping("/api/v1/delivery-points")
@RequiredArgsConstructor
public class DeliveryPointController {

    private final DeliveryPointService deliveryPointService;

    /**
     * Retrieves a list of active delivery points available in the authenticated user's campus.
     * The frontend uses this list to allow the user to select a delivery location for their order.
     * @param user The authenticated user, injected by Spring Security.
     * @return A list of valid {@link DeliveryPointResponse} objects.
     */
    @GetMapping
    public ResponseEntity<List<DeliveryPointResponse>> getActiveDeliveryPoints(@AuthenticationPrincipal User user) {
        List<DeliveryPointResponse> deliveryPoints = deliveryPointService.findActiveDeliveryPointsByCampus(user);
        return ResponseEntity.ok(deliveryPoints);
    }
}