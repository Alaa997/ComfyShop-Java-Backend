package nl.fontys.s3.comfyshop.bussiness.category.impl;

import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteCategoryUCImplTest {

    @Mock
    private CategoryRepository categoryRepositoryMock;
    @Mock
    private ProductRepository productRepositoryMock;
    @InjectMocks
    private DeleteCategoryUCImpl deleteCategoryUC;

    @Test
    void deleteCategory_success() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepositoryMock.existsById(categoryId)).thenReturn(true);
        when(productRepositoryMock.countByCategoryId(categoryId)).thenReturn(0);
        // Act
        deleteCategoryUC.deleteCategory(categoryId);
        // Assert
        verify(categoryRepositoryMock).deleteById(categoryId);
    }
    @Test
    void deleteCategory_shouldThrowException_whenCategoryIdNotExists() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepositoryMock.existsById(categoryId)).thenReturn(false);
        // Act and Assert
        assertThrows(InvalidCategoryException.class, () -> deleteCategoryUC.deleteCategory(categoryId));
        verify(categoryRepositoryMock, never()).deleteById(categoryId);
    }
    @Test
    void deleteCategory_shouldThrowException_whenCategoryAssociatedWithProducts() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepositoryMock.existsById(categoryId)).thenReturn(true);
        when(productRepositoryMock.countByCategoryId(categoryId)).thenReturn(1);
        // Act and Assert
        assertThrows(IllegalStateException.class, () -> deleteCategoryUC.deleteCategory(categoryId));
        verify(categoryRepositoryMock, never()).deleteById(categoryId);
    }
}