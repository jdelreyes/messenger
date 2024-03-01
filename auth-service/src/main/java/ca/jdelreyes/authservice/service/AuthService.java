package ca.jdelreyes.authservice.service;

import ca.jdelreyes.authservice.dto.AuthResponse;
import ca.jdelreyes.authservice.dto.user.LoginRequest;
import ca.jdelreyes.authservice.dto.user.RegisterRequest;

public interface AuthService {
    boolean register(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);
}
