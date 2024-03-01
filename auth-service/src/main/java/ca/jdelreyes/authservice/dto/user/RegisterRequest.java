package ca.jdelreyes.authservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
