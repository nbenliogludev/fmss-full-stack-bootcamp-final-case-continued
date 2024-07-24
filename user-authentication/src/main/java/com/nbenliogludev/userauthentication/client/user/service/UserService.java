package com.nbenliogludev.userauthentication.client.user.service;


import com.nbenliogludev.userauthentication.client.user.dto.response.UserResponse;
import com.nbenliogludev.userauthentication.client.user.UserClient;
import com.nbenliogludev.userauthentication.dto.request.UserCreateRequest;
import com.nbenliogludev.userauthentication.dto.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserClient userClient;

    public GenericResponse<UserResponse> createUser(UserCreateRequest request) {
        GenericResponse<UserResponse> response = userClient.createUser(request);
        if (response == null || !HttpStatus.OK.equals(response.getHttpStatus())) {
            log.error("Error Message: {}", response.getMessage());
        }
        return response;
    }
}
