package nl.fontys.s3.comfyshop.bussiness.category.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.bussiness.category.CreateCategoryUC;
import nl.fontys.s3.comfyshop.bussiness.exception.NameAlreadyExistsException;
import nl.fontys.s3.comfyshop.mappers.CategoryMapper;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCategoryUCImpl implements CreateCategoryUC {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (existsByName(categoryDTO.getName())) {
            throw new NameAlreadyExistsException();
        }
        CategoryEntity savedCategory = saveCategory(CategoryMapper.mapperToEntity(categoryDTO));
        return CategoryMapper.mapperToDTO(savedCategory);
    }

    private CategoryEntity saveCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    private boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
