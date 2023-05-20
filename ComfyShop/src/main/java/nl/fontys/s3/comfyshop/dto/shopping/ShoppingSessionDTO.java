package nl.fontys.s3.comfyshop.dto.shopping;

import lombok.*;
import nl.fontys.s3.comfyshop.dto.user.UserDTO;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingSessionDTO {
    private Long id;
    private UserDTO user;
    private List<CartItemDTO> cartItems;
}
