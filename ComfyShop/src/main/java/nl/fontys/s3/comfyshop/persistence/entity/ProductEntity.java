package nl.fontys.s3.comfyshop.persistence.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductEntity {
    private Long id;
    private String name;
    private Character unit;
    private Double price;
    private CategoryEntity category;
}
