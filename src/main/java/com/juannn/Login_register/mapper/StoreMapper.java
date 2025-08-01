package com.juannn.Login_register.mapper;

import com.juannn.Login_register.dto.store.request.StoreRequest;
import com.juannn.Login_register.dto.store.response.StoreResponse;
import com.juannn.Login_register.model.store.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = CampusMapper.class)
public interface StoreMapper {
    Store toStore(StoreRequest request);

    @Mapping(source = "seller.id", target = "sellerId")
    @Mapping(source = "averageRating", target = "averageRating")
    StoreResponse toStoreResponse(Store store);
}