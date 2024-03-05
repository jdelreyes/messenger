package ca.jdelreyes.userservice.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserPasswordRequest {
    private String oldPassword;
    @Size(min = 8)
    private String newPassword;
}
