package nl.fontys.s3.comfyshop.bussiness.category;

import nl.fontys.s3.comfyshop.dto.CategoryDTO;

public interface CreateCategoryUC {
    CategoryDTO createCategory(CategoryDTO request);
}
