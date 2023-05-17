package nl.fontys.s3.comfyshop.dto.user;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    public String email;
    @NotBlank
    public String password;
}
