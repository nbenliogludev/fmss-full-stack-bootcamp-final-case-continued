package com.nbenliogludev.userservice.service.impl;

import com.nbenliogludev.userservice.dto.request.UserCreateRequest;
import com.nbenliogludev.userservice.dto.response.UserResponse;
import com.nbenliogludev.userservice.entity.User;
import com.nbenliogludev.userservice.exception.UserEmailAlreadyExistException;
import com.nbenliogludev.userservice.exception.UserNotFoundException;
import com.nbenliogludev.userservice.mapper.UserMapper;
import com.nbenliogludev.userservice.repository.UserRepository;
import com.nbenliogludev.userservice.service.UserService;
import com.nbenliogludev.userservice.util.AppLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author nbenliogludev
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AppLogger appLogger;

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        // Check if user already exists
        appLogger.logInfo("UserServiceImpl","Attempting to create user with email: " + request.email());
        Optional<User> userOptional = userRepository.findByEmail(request.email());

        if (userOptional.isPresent()) {
            appLogger.logInfo("UserServiceImpl","User creation failed, email already exists: " + request.email());
            throw new UserEmailAlreadyExistException("User already exists with email: " + request.email());
        }

        User user = userMapper.mapUserCreateRequestToUser(request);
        user = userRepository.save(user);
        appLogger.logInfo("UserServiceImpl","User created successfully with id: " + user.getId());

        return userMapper.mapToUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        appLogger.logInfo("UserServiceImpl","Fetching all users");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        appLogger.logInfo("UserServiceImpl","Fetching user by id: " + id);
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            appLogger.logInfo("UserServiceImpl","User not found with id: " + id);
            throw new UserNotFoundException("User not found with id: " + id);
        }

        return userMapper.mapToUserResponse(userOptional.get());
    }

    @Override
    public UserResponse updateUser(Long id, UserCreateRequest request) {
        appLogger.logInfo("UserServiceImpl","Updating user with id: " + id);
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            appLogger.logInfo("UserServiceImpl","User not found, cannot update with id: " + id);
            throw new UserNotFoundException("User not found with id: " + id);
        }

        User user = userOptional.get();
        user.setName(request.name());
        user.setSurname(request.surname());
        user.setEmail(request.email());
        userRepository.save(user);
        appLogger.logInfo("UserServiceImpl","User updated successfully with id: " + user.getId());

        return userMapper.mapToUserResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        appLogger.logInfo("UserServiceImpl","Deleting user with id: " + id);
        userRepository.deleteById(id);
        appLogger.logInfo("UserServiceImpl","User deleted successfully with id: " + id);
    }
}
