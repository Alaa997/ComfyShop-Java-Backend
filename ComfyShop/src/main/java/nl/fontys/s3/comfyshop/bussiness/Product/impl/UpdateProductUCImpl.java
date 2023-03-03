package nl.fontys.s3.comfyshop.bussiness.Product.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.Product.UpdateProductUC;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidProductException;
import nl.fontys.s3.comfyshop.domain.Product.UpdateProductRequest;
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
    public void updateProduct(UpdateProductRequest request) {
        Optional<ProductEntity> productOptional = productRepository.findById(request.getId());
        if (productOptional.isEmpty()) {
            throw new InvalidProductException("PRODUCT_ID_INVALID");
        }

        ProductEntity product = productOptional.get();
        updateFields(request, product);
    }
    private void updateFields(UpdateProductRequest request, ProductEntity product) {
        CategoryEntity categoryEntity = categoryRepository.findById(request.getCategoryId());
        product.setCategory(categoryEntity);
        product.setName(request.getName());
        product.setUnit(request.getUnit());
        product.setPrice(request.getPrice());

        productRepository.save(product);
    }
}
