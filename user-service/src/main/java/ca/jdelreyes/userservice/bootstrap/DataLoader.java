package ca.jdelreyes.userservice.bootstrap;

import ca.jdelreyes.userservice.model.Role;
import ca.jdelreyes.userservice.model.User;
import ca.jdelreyes.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findUserByUserName("admin") == null) {
            userRepository.save(User.builder()
                    .userName("admin")
                    .firstName("firstName")
                    .lastName("lastName")
                    .password(bCryptPasswordEncoder.encode("password"))
                    .email("admin@domain.ca")
                    .roles(Collections
                            .singletonList(Role
                                    .builder()
                                    .name("ADMIN")
                                    .build()))
                    .build());
        }

        if (userRepository.findUserByUserName("johndoe") == null) {
            userRepository.save(User.builder()
                    .userName("johndoe")
                    .firstName("john")
                    .lastName("doe")
                    .password(bCryptPasswordEncoder.encode("password"))
                    .email("johndoe@domain.ca")
                    .roles(Collections
                            .singletonList(Role
                                    .builder()
                                    .name("USER")
                                    .build()))
                    .build());
        }
    }

}
