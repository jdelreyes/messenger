package ca.jdelreyes.userservice.service;

import ca.jdelreyes.userservice.dto.CreateUserRequest;
import ca.jdelreyes.userservice.dto.UpdateUserPasswordRequest;
import ca.jdelreyes.userservice.dto.UpdateUserRequest;
import ca.jdelreyes.userservice.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();

    UserResponse getUserById(Long userId);

    UserResponse getUserByUserName(String userName);

    UserResponse createUser(CreateUserRequest createUserRequest);

    UserResponse updateUser(Long userId, UpdateUserRequest updateUserRequest);

    void UpdateUserPassword(Long userId, UpdateUserPasswordRequest updateUserPasswordRequest);

    void deleteUser(Long userId);
}
