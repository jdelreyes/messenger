package ca.jdelreyes.authservice.service.jwt;

import ca.jdelreyes.authservice.dto.AuthResponse;
import ca.jdelreyes.authservice.dto.LoginRequest;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService {

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public AuthResponse generateToken(LoginRequest loginRequest) {
        try {
            GenerateKeyPair();

            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

            String accessToken = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);

            return new AuthResponse(accessToken);
        } catch (JWTCreationException ignored) {

        }
        return null;
    }

    public void GenerateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            keyPairGenerator.initialize(1024);

            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            privateKey = (RSAPrivateKey) keyPair.getPrivate();
            publicKey = (RSAPublicKey) keyPair.getPublic();

        } catch (Exception ignored) {

        }
    }
}
