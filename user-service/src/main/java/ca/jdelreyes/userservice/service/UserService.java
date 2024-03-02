package ca.jdelreyes.userservice.service;

import ca.jdelreyes.userservice.dto.CreateUserRequest;
import ca.jdelreyes.userservice.dto.UpdateUserRequest;
import ca.jdelreyes.userservice.dto.UserResponse;
import ca.jdelreyes.userservice.model.User;

import java.util.List;

public interface UserService {
    public List<UserResponse> getUsers();

    public UserResponse getUser(Long userId);

    public UserResponse createUser(CreateUserRequest createUserRequest);

    public UserResponse updateUser(Long userId, UpdateUserRequest updateUserRequest);

    public UserResponse deleteUser(Long userId);
}
