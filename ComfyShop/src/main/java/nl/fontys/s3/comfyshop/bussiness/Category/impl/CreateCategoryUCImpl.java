package nl.fontys.s3.comfyshop.bussiness.Category.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.Category.CreateCategoryUC;
import nl.fontys.s3.comfyshop.bussiness.exception.NameAlreadyExistsException;
import nl.fontys.s3.comfyshop.domain.Category.CreateCategoryRequest;
import nl.fontys.s3.comfyshop.domain.Category.CreateCategoryResponse;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCategoryUCImpl implements CreateCategoryUC {
    private final CategoryRepository categoryRepository;

    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new NameAlreadyExistsException();
        }
        CategoryEntity savedCategory = saveNewCategory(request);
        return CreateCategoryResponse.builder()
                .categoryId(savedCategory.getId())
                .name(savedCategory.getName())
                .build();
    }
    private CategoryEntity saveNewCategory(CreateCategoryRequest request) {
        CategoryEntity newCategory = CategoryEntity.builder()
                .name(request.getName())
                .build();
        return categoryRepository.save(newCategory);
    }
}
