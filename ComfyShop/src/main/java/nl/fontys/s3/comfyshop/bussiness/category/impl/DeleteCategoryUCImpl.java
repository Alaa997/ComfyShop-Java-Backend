package nl.fontys.s3.comfyshop.bussiness.category.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.category.DeleteCategoryUC;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCategoryUCImpl implements DeleteCategoryUC {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    @Override
    public void deleteCategory(Long categoryId) {
        if (!existsById(categoryId)) {
            throw new InvalidCategoryException("CATEGORY_ID_INVALID");
        }

        if (productRepository.countByCategoryId(categoryId) == 0) {
            delete(categoryId);
        } else {
            throw new IllegalStateException("Cannot delete category with associated products");
        }
    }
    private void delete(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
    private boolean existsById(Long categoryId) {
        return categoryRepository.existsById(categoryId);
    }
}
