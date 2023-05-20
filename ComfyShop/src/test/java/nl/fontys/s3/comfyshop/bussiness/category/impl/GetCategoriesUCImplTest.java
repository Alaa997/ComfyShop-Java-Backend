package nl.fontys.s3.comfyshop.bussiness.category.impl;

import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCategoriesUCImplTest {
    @Mock
    private CategoryRepository categoryRepositoryMock;
    @InjectMocks
    private nl.fontys.s3.comfyshop.bussiness.category.impl.GetCategoriesUCImpl getCategoriesUC;

    @Test
    void getCategories_Success() {
        // Arrange
        CategoryEntity meatEntity = CategoryEntity.builder().id(1L).name("Meat").build();
        CategoryEntity drinkEntity = CategoryEntity.builder().id(2L).name("Coffee").build();

        when(categoryRepositoryMock.findAll()).thenReturn(List.of(meatEntity, drinkEntity));

        // Act
        List<CategoryDTO> actualResult = getCategoriesUC.getCategories();

        CategoryDTO meatDTO= CategoryDTO.builder().id(1L).name("Meat").build();
        CategoryDTO drinkDTO = CategoryDTO.builder().id(2L).name("Coffee").build();
        List<CategoryDTO> expectedResult = List.of(meatDTO, drinkDTO);
        // Assert
        assertEquals(expectedResult, actualResult);
        verify(categoryRepositoryMock).findAll();
    }
}