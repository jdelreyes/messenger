package ca.jdelreyes.authservice.service;

import ca.jdelreyes.authservice.dto.LogInRequest;
import ca.jdelreyes.authservice.dto.RegisterRequest;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service()
@Slf4j()
@RequiredArgsConstructor()
public class AuthServiceImpl implements AuthService {
    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;


    @Override
    public RegisterRequest register(RegisterRequest registerRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(userRepresentation.getUsername());
        userRepresentation.setEmail(userRepresentation.getEmail());
        userRepresentation.setFirstName(userRepresentation.getFirstName());
        userRepresentation.setLastName(userRepresentation.getLastName());

        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(registerRequest.getPassword());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        List<CredentialRepresentation> credentialRepresentationList = new ArrayList<>();
        credentialRepresentationList.add(credentialRepresentation);

        userRepresentation.setCredentials(credentialRepresentationList);

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(userRepresentation);

        if (Objects.equals(201, response.getStatus())) {
            return registerRequest;
        }

//        response.readEntity()

        return null;
    }

    @Override
    public void login(LogInRequest logInRequest) {

    }


    @Override
    public UserRepresentation getUser(String userId) {
        return getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public void deleteUser(String userId) {
        getUsersResource().delete(userId);
    }


    private UsersResource getUsersResource() {
        RealmResource realmResource = keycloak.realm(realm);
        return realmResource.users();
    }
}
