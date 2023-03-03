package nl.fontys.s3.comfyshop.persistence.impl;

import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeProductRepositoryImpl implements ProductRepository {
    private static Long nextId = 1L;

    private final List<ProductEntity> savedProducts;

    public FakeProductRepositoryImpl() {
        this.savedProducts = new ArrayList<>();
    }

    @Override
    public boolean existsByName(String name) {
        return this.savedProducts
                .stream()
                .anyMatch(productEntity -> productEntity.getName().equals(name));
    }

    @Override
    public List<ProductEntity> findAllByCategoryId(Long categoryId) {
        return this.savedProducts
                .stream()
                .filter(productEntity -> productEntity.getCategory().getId().equals(categoryId))
                .toList();
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        if (product.getId() == null) {
            product.setId(nextId);
            nextId++;
            this.savedProducts.add(product);
        }
        return product;
    }

    @Override
    public void deleteById(long productId) {
        this.savedProducts.removeIf(productEntity -> productEntity.getId().equals(productId));
    }

    @Override
    public Optional<ProductEntity> findById(long productId) {
        return this.savedProducts.stream()
                .filter(productEntity -> productEntity.getId().equals(productId))
                .findFirst();
    }
}
