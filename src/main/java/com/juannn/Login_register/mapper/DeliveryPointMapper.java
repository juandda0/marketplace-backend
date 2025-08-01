package com.juannn.Login_register.mapper;

import com.juannn.Login_register.dto.delivery_point.request.DeliveryPointRequest;
import com.juannn.Login_register.dto.delivery_point.response.DeliveryPointResponse;
import com.juannn.Login_register.model.uni.DeliveryPoint;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CampusMapper.class)
public interface DeliveryPointMapper {
    DeliveryPoint toDeliveryPoint(DeliveryPointRequest request);
    DeliveryPointResponse toDeliveryPointResponse(DeliveryPoint deliveryPoint);
}