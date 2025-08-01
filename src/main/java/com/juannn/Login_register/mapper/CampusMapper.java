package com.juannn.Login_register.mapper;

import com.juannn.Login_register.dto.campus.request.CampusRequest;
import com.juannn.Login_register.dto.campus.response.CampusResponse;
import com.juannn.Login_register.model.uni.Campus;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = UniversityMapper.class)
public interface CampusMapper {
    Campus toCampus(CampusRequest request);
    CampusResponse toCampusResponse(Campus campus);
}