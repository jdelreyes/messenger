package ca.jdelreyes.authservice.controller;

import ca.jdelreyes.authservice.dto.RegisterRequest;
import ca.jdelreyes.authservice.dto.SignInRequest;
import ca.jdelreyes.authservice.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/auth")
@RequiredArgsConstructor()
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public void register(@RequestBody() RegisterRequest registerRequest) {
        System.out.println(registerRequest);
//        return
        authService.register();
    }

    @PostMapping("/signIn")
    public void signIn(@RequestBody() SignInRequest signInRequest) {
        System.out.println();
//        return
        authService.signIn();
    }
}
