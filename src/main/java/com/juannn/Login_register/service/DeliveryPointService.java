package com.juannn.Login_register.service;

import com.juannn.Login_register.dto.delivery_point.response.DeliveryPointResponse;
import com.juannn.Login_register.mapper.DeliveryPointMapper;
import com.juannn.Login_register.model.uni.DeliveryPoint;
import com.juannn.Login_register.model.user.User;
import com.juannn.Login_register.repository.DeliveryPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for delivery point related business logic.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryPointService {

    private final DeliveryPointRepository deliveryPointRepository;
    private final DeliveryPointMapper deliveryPointMapper;

    /**
     * Finds all active delivery points associated with the authenticated user's campus.
     * @param user The authenticated user.
     * @return A list of active delivery point DTOs for the user's campus.
     */
    public List<DeliveryPointResponse> findActiveDeliveryPointsByCampus(User user) {
        Long campusId = user.getCampus().getId();
        List<DeliveryPoint> points = deliveryPointRepository.findByCampus_IdAndIsActiveTrue(campusId);
        return points.stream()
                .map(deliveryPointMapper::toDeliveryPointResponse)
                .collect(Collectors.toList());
    }
}