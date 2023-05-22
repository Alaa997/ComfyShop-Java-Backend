package nl.fontys.s3.comfyshop.dto.shopping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.comfyshop.dto.user.UserDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingSessionDTO {
    private Long id;
    private UserDTO user;
    @Builder.Default
    private List<CartItemDTO> cartItems = new ArrayList<>();
    private boolean ordered;
}
