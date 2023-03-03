package nl.fontys.s3.comfyshop.bussiness.Product.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.Product.CreateProductUC;
import nl.fontys.s3.comfyshop.bussiness.exception.NameAlreadyExistsException;
import nl.fontys.s3.comfyshop.domain.Product.CreateProductRequest;
import nl.fontys.s3.comfyshop.domain.Product.CreateProductResponse;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateProductUCImpl implements CreateProductUC {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public CreateProductResponse createProduct(CreateProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new NameAlreadyExistsException();
        }
        ProductEntity savedProduct = saveNewProduct(request);
        return CreateProductResponse.builder()
                .productId(savedProduct.getId())
                .build();
    }

    private ProductEntity saveNewProduct(CreateProductRequest request) {
        CategoryEntity categoryEntity = categoryRepository.findById(request.getCategoryId());

        ProductEntity newProduct = ProductEntity.builder()
                .category(categoryEntity)
                .name(request.getName())
                .unit(request.getUnit())
                .price(request.getPrice())
                .build();
        return productRepository.save(newProduct);
    }
}
