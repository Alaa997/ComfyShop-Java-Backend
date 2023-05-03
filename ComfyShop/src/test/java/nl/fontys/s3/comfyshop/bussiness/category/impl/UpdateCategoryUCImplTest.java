package nl.fontys.s3.comfyshop.bussiness.category.impl;

import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
class UpdateCategoryUCImplTest {
    @Mock
    private CategoryRepository categoryRepositoryMock;
    @InjectMocks
    private UpdateCategoryUCImpl updateCategoryUC;

    @Test
    void updateCategory_Success() {
        // Arrange
        Long categoryId = 1L;
        CategoryDTO request = CategoryDTO.builder().id(categoryId).name("New Meat").build();

        CategoryEntity categoryEntity = CategoryEntity.builder().id(1L).name("Meat").build();
        when(categoryRepositoryMock.findById(categoryId)).thenReturn(Optional.of(categoryEntity));

        // Act
        updateCategoryUC.updateCategory(request);

        // Assert
        assertEquals(categoryEntity.getName(), request.getName());
        verify(categoryRepositoryMock, times(1)).findById(categoryId);
        verify(categoryRepositoryMock, times(1)).save(categoryEntity);
    }

    @Test
    void updateCategory_shouldThrowException_whenInvalidCategoryId() {
        // Arrange
        Long categoryId = 1L;
        CategoryDTO request = CategoryDTO.builder().id(categoryId).name("New Meat").build();

        when(categoryRepositoryMock.findById(categoryId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidCategoryException.class, () -> updateCategoryUC.updateCategory(request));
    }
}