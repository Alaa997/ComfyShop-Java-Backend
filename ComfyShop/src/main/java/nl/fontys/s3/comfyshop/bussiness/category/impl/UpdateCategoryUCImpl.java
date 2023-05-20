package nl.fontys.s3.comfyshop.bussiness.category.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.bussiness.category.UpdateCategoryUC;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCategoryUCImpl implements UpdateCategoryUC {
    private final CategoryRepository categoryRepository;
    @Override
    public void updateCategory(CategoryDTO request) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(request.getId());
        if (categoryOptional.isEmpty()){
            throw new InvalidCategoryException("CATEGORY_ID_INVALID");
        }
        CategoryEntity categoryEntity = categoryOptional.get();
        UpdateFields(request, categoryEntity);
    }
    private void UpdateFields(CategoryDTO request, CategoryEntity category) {
        category.setName(request.getName());
        categoryRepository.save(category);
    }
}
