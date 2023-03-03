package nl.fontys.s3.comfyshop.domain.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private Long id;
    @NotBlank(message = "Product name is required")
    private String name;
    @NotNull(message = "Product price is required")
    @PositiveOrZero(message = "Product price must be a positive or zero value")
    @Pattern(regexp = "[A-Z]")
    private Character unit;
    @NotBlank
    @Positive
    private Double price;
    @NotNull(message = "Product category ID is required")
    private Long categoryId;
}
