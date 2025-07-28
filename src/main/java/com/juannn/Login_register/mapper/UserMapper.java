package com.juannn.Login_register.mapper;

import com.juannn.Login_register.dto.auth.request.RegisterRequest;
import com.juannn.Login_register.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    //Map DTOs <-> Dom
    @Mapping(target = "id", ignore = true)
    User toDomain(RegisterRequest request);
    RegisterRequest toResponse(User user);
}
