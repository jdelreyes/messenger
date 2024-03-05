package ca.jdelreyes.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
    @Size(min = 2)
    private String userName;
    @Email
    private String email;
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
}
