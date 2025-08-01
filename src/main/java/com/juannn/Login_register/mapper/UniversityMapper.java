package com.juannn.Login_register.mapper;

import com.juannn.Login_register.dto.university.request.UniversityRequest;
import com.juannn.Login_register.dto.university.response.UniversityResponse;
import com.juannn.Login_register.model.uni.University;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UniversityMapper {
    University toUniversity(UniversityRequest request);
    UniversityResponse toUniversityResponse(University university);
}