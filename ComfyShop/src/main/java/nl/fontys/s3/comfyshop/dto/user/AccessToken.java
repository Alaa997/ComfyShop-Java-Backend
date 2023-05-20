package nl.fontys.s3.comfyshop.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken {
    private String subject;
    private List<String> roles;
    private Long userId;
    private Long shoppingSessionId;

    @JsonIgnore
    public boolean hasRole(String roleName) {
        if (roles == null) {
            return false;
        }
        return roles.contains(roleName);
    }
}
