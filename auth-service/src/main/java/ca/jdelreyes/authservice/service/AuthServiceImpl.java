package ca.jdelreyes.authservice.service;

import ca.jdelreyes.authservice.dto.AuthResponse;
import ca.jdelreyes.authservice.dto.user.LoginRequest;
import ca.jdelreyes.authservice.dto.user.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service()
@RequiredArgsConstructor()
@Slf4j()
public class AuthServiceImpl implements AuthService {
    private final WebClient webClient;

    @Override
    public boolean register(RegisterRequest registerRequest) {
        return false;
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        return null;
    }

    private boolean userExists() {
//        UserResponse userResponse = webClient.post();

        return true;
    }


}
