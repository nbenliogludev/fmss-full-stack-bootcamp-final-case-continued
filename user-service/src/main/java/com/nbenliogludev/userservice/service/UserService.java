package com.nbenliogludev.userservice.service;

import com.nbenliogludev.userservice.dto.request.UserCreateRequest;
import com.nbenliogludev.userservice.dto.response.UserResponse;

import java.util.List;

/**
 * @author nbenliogludev
 */
public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserCreateRequest userDetails);

    void deleteUser(Long id);
}
