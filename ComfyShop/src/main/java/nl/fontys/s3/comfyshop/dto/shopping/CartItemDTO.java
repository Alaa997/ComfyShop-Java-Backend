package nl.fontys.s3.comfyshop.dto.shopping;

import lombok.*;
import nl.fontys.s3.comfyshop.dto.ProductDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private Long sessionId;
    private ProductDTO product;
    private int quantity;
}
