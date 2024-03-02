package ca.jdelreyes.authservice.service;

import ca.jdelreyes.authservice.dto.AuthResponse;
import ca.jdelreyes.authservice.dto.LoginRequest;
import ca.jdelreyes.authservice.dto.RegisterRequest;
import ca.jdelreyes.authservice.dto.user.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest registerRequest);

    AuthResponse login(LoginRequest loginRequest);
}
