package nl.fontys.s3.comfyshop.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    public Long id;
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public String address;
}
