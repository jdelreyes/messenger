package ca.jdelreyes.authservice.service;

import ca.jdelreyes.authservice.dto.AuthResponse;
import ca.jdelreyes.authservice.dto.LoginRequest;
import ca.jdelreyes.authservice.dto.RegisterRequest;
import ca.jdelreyes.authservice.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service()
@RequiredArgsConstructor()
@Slf4j()
public class AuthServiceImpl implements AuthService {
    private final WebClient webClient;

    @Value("${user.service.url}")
    private String userServiceUri;

    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        if (userExists(registerRequest.getUserName()))
            return null;

        return this.registerUser(registerRequest);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    private boolean userExists(String userName) {
        WebClient.ResponseSpec responseSpec = webClient
                .get()
                .uri(userServiceUri + "/" + userName)
                .retrieve();

        ResponseEntity<UserResponse> userResponseResponseEntity = responseSpec.toEntity(UserResponse.class).block();

        assert userResponseResponseEntity != null;
        if (Objects.equals(userResponseResponseEntity.getStatusCode(), HttpStatus.OK))
            return true;
        if (Objects.equals(userResponseResponseEntity.getStatusCode(), HttpStatus.NOT_FOUND))
            return false;
        return false;
    }

    private UserResponse registerUser(RegisterRequest registerRequest) {
        return webClient
                .post()
                .uri(userServiceUri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(registerRequest)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
    }
}
