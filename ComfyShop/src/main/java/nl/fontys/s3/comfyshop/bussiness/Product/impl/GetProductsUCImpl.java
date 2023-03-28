package nl.fontys.s3.comfyshop.bussiness.product.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.DTO.ProductDTO;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.bussiness.product.GetProductsUC;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetProductsUCImpl implements GetProductsUC {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public List<ProductDTO> getProducts(long categoryId) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new InvalidCategoryException("CATEGORY_ID_INVALID");
        }

        List<ProductEntity> results;
        if (productRepository.countByCategoryId(categoryId) > 0) {
            results = productRepository.findAllByCategoryId(categoryId);
        } else {
            results = new ArrayList<>();
        }
        return ProductMapper.mapperToDTOList(results);
    }
}
