package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.uni.Campus;
import com.juannn.Login_register.model.uni.DeliveryPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryPointRepository extends JpaRepository<DeliveryPoint, Long> {
    List<DeliveryPoint> findByCampus_Id(Long campusId);
    List<DeliveryPoint> findByCampus_IdAndIsActiveTrue(Long campusId);
}
