package nl.fontys.s3.comfyshop.domain.Category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCategoryResponse {
    private Long categoryId;
    private String name;
}
