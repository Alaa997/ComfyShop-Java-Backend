package nl.fontys.s3.comfyshop.persistence.impl;

import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class FakeCategoryRepositoryImpl implements CategoryRepository {
    private static Long nextId = 1L;
    private final List<CategoryEntity> savedCategories;

    public FakeCategoryRepositoryImpl() {
        this.savedCategories = new ArrayList<>();
    }

    @Override
    public CategoryEntity save(CategoryEntity category) {
        category.setId(nextId);
        nextId++;
        this.savedCategories.add(category);
        return category;
    }

    @Override
    public List<CategoryEntity> findAll() {
        return Collections.unmodifiableList(savedCategories);
    }

    @Override
    public boolean existsByName(String name) {
        return this.savedCategories
                .stream()
                .anyMatch(categoryEntity -> categoryEntity.getName().equals(name));
    }

    @Override
    public boolean existsById(long categoryId) {
        return this.savedCategories
                .stream()
                .anyMatch(categoryEntity -> categoryEntity.getId() == categoryId);
    }

    @Override
    public CategoryEntity findById(long categoryId) {
        return this.savedCategories
                .stream()
                .filter(categoryEntity -> categoryEntity.getId() == categoryId)
                .findFirst()
                .orElse(null);
    }
}
