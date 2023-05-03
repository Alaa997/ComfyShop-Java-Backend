package nl.fontys.s3.comfyshop.bussiness.product.impl;

import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
import nl.fontys.s3.comfyshop.persistence.CategoryRepository;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetProductsByCategoryUCImplTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private CategoryRepository categoryRepositoryMock;
    @Mock
    private ProductMapper productMapperMock;
    @InjectMocks
    private GetProductsByCategoryUCImpl getProductsByCategoryUC;
    @Test
    void getProducts_success() {
        // Arrange
        Long categoryId = 1L;
        CategoryEntity categoryEntity = CategoryEntity.builder().id(categoryId).name("Meat").build();
        ProductEntity product1 = ProductEntity.builder()
                .id(1L)
                .name("Ribs1")
                .description("Tasty1")
                .price(5.5)
                .category(categoryEntity)
                .build();
        ProductEntity product2 = ProductEntity.builder()
                .id(2L)
                .name("Ribs2")
                .description("Tasty2")
                .price(6.5)
                .category(categoryEntity)
                .build();
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(product1);
        productEntityList.add(product2);

        when(categoryRepositoryMock.findById(categoryId)).thenReturn(Optional.of(categoryEntity));
        when(productRepositoryMock.countByCategoryId(categoryId)).thenReturn(2);
        when(productRepositoryMock.findAllByCategoryId(categoryId)).thenReturn(productEntityList);

        List<ProductDTO> expected = productMapperMock.mapperToDTOList(productEntityList);

        // Act
        List<ProductDTO> actual = getProductsByCategoryUC.getProductsByCategoryId(categoryId);

        // Assert
        assertEquals(expected.size(), actual.size());

    }
    @Test
    void getProducts_invalidCategoryId() {
        // Arrange
        Long categoryId = 1L;

        when(categoryRepositoryMock.findById(categoryId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidCategoryException.class, () -> getProductsByCategoryUC.getProductsByCategoryId(categoryId));
        verify(productRepositoryMock, never()).findAllByCategoryId(categoryId);
        verify(productRepositoryMock, never()).countByCategoryId(categoryId);
    }
    @Test
    void getProducts_noProductsForCategoryId() {
        // Arrange
        Long categoryId = 1L;
        CategoryEntity categoryEntity = CategoryEntity.builder().id(categoryId).name("Meat").build();

        when(categoryRepositoryMock.findById(categoryId)).thenReturn(Optional.of(categoryEntity));
        when(productRepositoryMock.countByCategoryId(categoryId)).thenReturn(0);

        // Act
        List<ProductDTO> actual = getProductsByCategoryUC.getProductsByCategoryId(categoryId);

        // Assert
        assertEquals(0, actual.size());
        verify(categoryRepositoryMock).findById(categoryId);
        verify(productRepositoryMock, never()).findAllByCategoryId(categoryId);
    }
}