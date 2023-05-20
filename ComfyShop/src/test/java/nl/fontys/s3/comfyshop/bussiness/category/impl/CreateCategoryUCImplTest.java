package nl.fontys.s3.comfyshop.bussiness.category.impl;

import nl.fontys.s3.comfyshop.bussiness.exception.NameAlreadyExistsException;
import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCategoryUCImplTest {
    @Mock
    private CategoryRepository categoryRepositoryMock;
    @InjectMocks
    private nl.fontys.s3.comfyshop.bussiness.category.impl.CreateCategoryUCImpl createCategoryUC;

    @Test
    void createCategorySuccess() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().name("Meat").build();
        when(categoryRepositoryMock.existsByName(categoryDTO.getName())).thenReturn(false);

        CategoryEntity savedCategoryEntity = CategoryEntity.builder().id(1L).name(categoryDTO.getName()).build();
        CategoryEntity expectedNewCategory = CategoryEntity.builder().name("Meat").build();
        when(categoryRepositoryMock.save(expectedNewCategory)).thenReturn(savedCategoryEntity);
        // Act
        CategoryDTO createdCategoryDTO = createCategoryUC.createCategory(categoryDTO);

        // Assert
        CategoryDTO expected = CategoryDTO.builder().id(1L).name("Meat").build();
        assertEquals(expected, createdCategoryDTO);
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