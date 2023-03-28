package nl.fontys.s3.comfyshop.bussiness.category.impl;

import nl.fontys.s3.comfyshop.DTO.CategoryDTO;
import nl.fontys.s3.comfyshop.bussiness.exception.NameAlreadyExistsException;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCategoryUCImplTest {
    @Mock
    private CategoryRepository categoryRepositoryMock;
    @InjectMocks
    private CreateCategoryUCImpl createCategoryUC;

    @Test
    void createCategorySuccess() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().name("Meat").build();
        when(categoryRepositoryMock.existsByName(categoryDTO.getName())).thenReturn(false);

        CategoryEntity savedCategoryEntity = CategoryEntity.builder().name(categoryDTO.getName()).build();
        when(categoryRepositoryMock.save(Mockito.any(CategoryEntity.class))).thenReturn(savedCategoryEntity);
        // Act
        CategoryDTO createdCategoryDTO = createCategoryUC.createCategory(categoryDTO);

        // Assert
        assertNotNull(createdCategoryDTO);
        assertEquals(categoryDTO.getName(), createdCategoryDTO.getName());
    }

    @Test
    void testCreateCategoryWithExistingName() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().name("Meat").build();
        when(categoryRepositoryMock.existsByName(categoryDTO.getName())).thenReturn(true);

        // Act and Assert
        assertThrows(NameAlreadyExistsException.class, () -> createCategoryUC.createCategory(categoryDTO));
    }

}