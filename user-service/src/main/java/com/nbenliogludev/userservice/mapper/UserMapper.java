package com.nbenliogludev.userservice.mapper;

import com.nbenliogludev.userservice.dto.request.UserCreateRequest;
import com.nbenliogludev.userservice.dto.response.UserResponse;
import com.nbenliogludev.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author nbenliogludev
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User mapUserCreateRequestToUser(UserCreateRequest request);

    UserResponse mapToUserResponse(User user);
}
