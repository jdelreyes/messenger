package ca.jdelreyes.userservice.service;

import ca.jdelreyes.userservice.dto.CreateUserRequest;
import ca.jdelreyes.userservice.dto.UpdateUserPasswordRequest;
import ca.jdelreyes.userservice.dto.UpdateUserRequest;
import ca.jdelreyes.userservice.dto.UserResponse;
import ca.jdelreyes.userservice.model.User;
import ca.jdelreyes.userservice.repository.RoleRepository;
import ca.jdelreyes.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;

@Service()
@RequiredArgsConstructor()
@Slf4j()
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

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
                    .roles(Collections.singletonList(roleRepository.findByName(("USER"))))
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
    public void UpdateUserPassword(Long userId, UpdateUserPasswordRequest updateUserPasswordRequest) {
        String oldPassword = updateUserPasswordRequest.getOldPassword();
        String newPassword = updateUserPasswordRequest.getNewPassword();
        String hash = userRepository.findUserById(userId).getPassword();

        boolean oldPasswordMatches = bCryptPasswordEncoder.matches(oldPassword, hash);

        if (!oldPasswordMatches) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteUserById(userId);
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
