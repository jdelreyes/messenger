package ca.jdelreyes.userservice.service;

import ca.jdelreyes.userservice.dto.CreateUserRequest;
import ca.jdelreyes.userservice.dto.UpdateUserRequest;
import ca.jdelreyes.userservice.dto.UserResponse;
import ca.jdelreyes.userservice.model.User;
import ca.jdelreyes.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
@RequiredArgsConstructor()
@Slf4j()
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(this::mapUserToUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(Long userId) {
        try {
            return mapUserToUserResponse(userRepository.findUserById(userId));
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public UserResponse getUserByUserName(String userName) {
        try {
            return mapUserToUserResponse(userRepository.findUserByUserName(userName));
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        try {
            User user = User.builder()
                    .userName(createUserRequest.getUserName())
                    .email(createUserRequest.getEmail())
                    .firstName(createUserRequest.getFirstName())
                    .lastName(createUserRequest.getLastName())
                    .password(bCryptPasswordEncoder.encode(createUserRequest.getPassword()))
                    .build();

            userRepository.save(user);
            return mapUserToUserResponse(user);
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public UserResponse updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        return null;
    }

    @Override
    public UserResponse deleteUser(Long userId) {
        try {
            return mapUserToUserResponse(userRepository.deleteUserById(userId));
        } catch (Exception exception) {
            return null;
        }
    }

    private boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }

    private UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(user.getRoles())
                .build();
    }
}
