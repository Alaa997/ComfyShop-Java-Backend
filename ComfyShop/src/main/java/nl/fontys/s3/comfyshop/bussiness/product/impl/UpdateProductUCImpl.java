package nl.fontys.s3.comfyshop.bussiness.product.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidProductException;
import nl.fontys.s3.comfyshop.bussiness.product.UpdateProductUC;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateProductUCImpl implements UpdateProductUC {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public void updateProduct(ProductDTO request) {
        Optional<ProductEntity> productOptional = productRepository.findById(request.getId());
        if (productOptional.isEmpty()) {
            throw new InvalidProductException("PRODUCT_ID_INVALID");
        }
        ProductEntity productEntity = productOptional.get();
        updateFields(request, productEntity);
    }
    private void updateFields(ProductDTO request, ProductEntity product) {
        Optional<CategoryEntity> categoryOptional = categoryRepository.findById(request.getCategory().getId());
        if (categoryOptional.isEmpty()){
            throw new InvalidCategoryException("CATEGORY_ID_INVALID");
        }
        product.setCategory(categoryOptional.get());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        productRepository.save(product);
    }
}
