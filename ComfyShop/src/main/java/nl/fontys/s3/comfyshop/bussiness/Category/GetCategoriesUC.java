package nl.fontys.s3.comfyshop.bussiness.category;

import nl.fontys.s3.comfyshop.DTO.CategoryDTO;

import java.util.List;

public interface GetCategoriesUC {
    List<CategoryDTO> getCategories();
}
