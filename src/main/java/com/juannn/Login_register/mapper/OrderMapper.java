package com.juannn.Login_register.mapper;

import com.juannn.Login_register.dto.order.response.OrderItemResponse;
import com.juannn.Login_register.dto.order.response.OrderPackageResponse;
import com.juannn.Login_register.dto.order.response.OrderResponse;
import com.juannn.Login_register.model.order.Order;
import com.juannn.Login_register.model.order.OrderItem;
import com.juannn.Login_register.model.order.OrderPackage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProductMapper.class, StoreMapper.class, DeliveryPointMapper.class})
public interface OrderMapper {

    @Mapping(source = "buyer.id", target = "buyerId")
    OrderResponse toOrderResponse(Order order);

    OrderPackageResponse toOrderPackageResponse(OrderPackage orderPackage);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}