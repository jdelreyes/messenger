package ca.jdelreyes.authservice.service;

import ca.jdelreyes.authservice.dto.AuthResponse;
import ca.jdelreyes.authservice.dto.LoginRequest;
import ca.jdelreyes.authservice.dto.RegisterRequest;
import ca.jdelreyes.authservice.dto.user.UserResponse;
import ca.jdelreyes.authservice.service.jwt.JwtServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service()
@RequiredArgsConstructor()
@Slf4j()
public class AuthServiceImpl implements AuthService {
    private final WebClient webClient;
    private final JwtServiceImpl jwtService;

    @Value("${user.service.url}")
    private String userServiceUri;

    @Override
    public UserResponse register(RegisterRequest registerRequest) {
        if (userExistsById(registerRequest.getUserName()))
            return null;

        UserResponse userResponse = postNewUserPayload(registerRequest);

        return userResponse;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return jwtService.generateToken(loginRequest);
    }

    private boolean userExistsById(String userName) {
        try {
            WebClient.ResponseSpec responseSpec = webClient
                    .get()
                    .uri(userServiceUri + "/" + userName)
                    .retrieve();

            responseSpec
                    .toEntity(UserResponse.class)
                    .block();
        } catch (WebClientResponseException webClientResponseException) {
            return false;
        }

        return true;

    }

    private UserResponse postNewUserPayload(RegisterRequest registerRequest) {
        ResponseEntity<UserResponse> userResponseResponseEntity;
        try {
            userResponseResponseEntity = webClient
                    .post()
                    .uri(userServiceUri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(registerRequest)
                    .retrieve()
                    .toEntity(UserResponse.class)
                    .block();

            assert userResponseResponseEntity != null;

        } catch (Exception exception) {
            return null;
        }

        return userResponseResponseEntity.getBody();
    }
}
