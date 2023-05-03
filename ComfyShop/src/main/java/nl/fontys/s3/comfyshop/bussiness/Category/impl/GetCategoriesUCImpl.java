package nl.fontys.s3.comfyshop.bussiness.category.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.bussiness.category.GetCategoriesUC;
import nl.fontys.s3.comfyshop.mappers.CategoryMapper;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCategoriesUCImpl implements GetCategoriesUC {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getCategories() {
        return CategoryMapper.mapperToDTOList(categoryRepository.findAll());
    }
}
