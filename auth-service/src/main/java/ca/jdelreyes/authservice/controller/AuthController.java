package ca.jdelreyes.authservice.controller;

import ca.jdelreyes.authservice.dto.LogInRequest;
import ca.jdelreyes.authservice.dto.RegisterRequest;
import ca.jdelreyes.authservice.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/auth")
@RequiredArgsConstructor()
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterRequest register(@RequestBody() RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public void login(@RequestBody() LogInRequest logInRequest) {

    }

    @GetMapping("/{userId}")
    public UserRepresentation getUser(@PathVariable("userId") String userId) {
        return this.authService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        authService.deleteUser(userId);
    }

}
