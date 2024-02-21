package ca.jdelreyes.authservice.service;

import ca.jdelreyes.authservice.dto.LogInRequest;
import ca.jdelreyes.authservice.dto.RegisterRequest;
import org.keycloak.representations.idm.UserRepresentation;

public interface AuthService {
    RegisterRequest register(RegisterRequest registerRequest);

    void login(LogInRequest logInRequest);

    UserRepresentation getUser(String userId);

    void deleteUser(String userId);
}
