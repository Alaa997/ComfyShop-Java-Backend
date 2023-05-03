package nl.fontys.s3.comfyshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    public Long id;
    public String name;
    public String description;
    public Double price;
    public CategoryDTO category;

}
