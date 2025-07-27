package com.juannn.Login_register.repository;

import com.juannn.Login_register.model.Campus;
import com.juannn.Login_register.model.DeliveryPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryPointRepository extends JpaRepository<Campus, Long> {
    List<DeliveryPoint> findByCampusId(Long campusId);
}
