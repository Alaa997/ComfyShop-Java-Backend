package nl.fontys.s3.comfyshop.persistence.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryEntity {
    private Long id;

    private String name;
}
