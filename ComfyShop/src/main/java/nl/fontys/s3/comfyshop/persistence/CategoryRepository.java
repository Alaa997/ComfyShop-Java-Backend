package nl.fontys.s3.comfyshop.persistence;

import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;

import java.util.List;

public interface CategoryRepository {
    CategoryEntity save(CategoryEntity category);
    CategoryEntity findById(long categoryId);
    List<CategoryEntity> findAll();

    boolean existsByName(String name);

    boolean existsById(long categoryId);

}
