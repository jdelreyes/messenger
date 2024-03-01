package ca.jdelreyes.authservice.controller;

import ca.jdelreyes.authservice.dto.AuthResponse;
import ca.jdelreyes.authservice.dto.user.LoginRequest;
import ca.jdelreyes.authservice.dto.user.RegisterRequest;
import ca.jdelreyes.authservice.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/auth")
@RequiredArgsConstructor()
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody() RegisterRequest registerRequest) {
        Boolean isUserRegistered = authService.register(registerRequest);
        if (isUserRegistered)
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody() LoginRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}
