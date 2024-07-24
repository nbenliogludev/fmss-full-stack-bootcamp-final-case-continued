package com.nbenliogludev.userauthentication.client.user;

import com.nbenliogludev.userauthentication.client.user.dto.response.UserResponse;
import com.nbenliogludev.userauthentication.dto.request.UserCreateRequest;
import com.nbenliogludev.userauthentication.dto.response.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-service")
public interface UserClient {

    @PostMapping("/api/v1/users")
    public GenericResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request);

}