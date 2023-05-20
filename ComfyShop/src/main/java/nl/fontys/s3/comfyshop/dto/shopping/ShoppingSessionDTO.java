package nl.fontys.s3.comfyshop.dto.shopping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.s3.comfyshop.dto.user.UserDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingSessionDTO {
    private Long id;
    private UserDTO user;
//    private List<CartItemDTO> cartItems;
    private boolean ordered;
}
