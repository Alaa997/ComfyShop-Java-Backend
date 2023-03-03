package nl.fontys.s3.comfyshop.domain.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductResponse {
    private Long productId;
    private String name;
    private Character unit;
    private Double price;
    private Long categoryId;
}
