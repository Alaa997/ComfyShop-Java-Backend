package nl.fontys.s3.comfyshop.persistence;

import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    boolean existsByName(String name);

    List<ProductEntity> findAllByCategoryId(Long categoryId);

    ProductEntity save(ProductEntity Product);

    void deleteById(long productId);

    Optional<ProductEntity> findById(long productId);
}
