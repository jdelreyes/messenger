package ca.jdelreyes.authservice.service.jwt;

import ca.jdelreyes.authservice.dto.AuthResponse;
import ca.jdelreyes.authservice.dto.LoginRequest;

public interface JwtService {
    AuthResponse generateToken(LoginRequest loginRequest);
}
