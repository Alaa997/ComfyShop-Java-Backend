package nl.fontys.s3.comfyshop.bussiness.product.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.bussiness.exception.NameAlreadyExistsException;
import nl.fontys.s3.comfyshop.bussiness.product.CreateProductUC;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class CreateProductUCImpl implements CreateProductUC {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (existsByName(productDTO.getName()))
            throw new NameAlreadyExistsException();

        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(productDTO.getCategory().getId());
        if (!categoryOptional.isPresent())
            throw new InvalidCategoryException("CATEGORY_ID_INVALID");

        ProductEntity savedProduct = save(ProductMapper.mapperToEntity(productDTO));
        return ProductMapper.mapperToDTO(savedProduct);
    }

    private ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    private boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

}
