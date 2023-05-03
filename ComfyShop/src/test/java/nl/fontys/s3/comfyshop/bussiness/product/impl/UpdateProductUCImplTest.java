package nl.fontys.s3.comfyshop.bussiness.product.impl;

import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidProductException;
import nl.fontys.s3.comfyshop.mappers.CategoryMapper;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProductUCImplTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private CategoryRepository categoryRepositoryMock;
    @Mock
    private ProductMapper productMapperMock;
    @Mock
    private CategoryMapper categoryMapperMock;
    @InjectMocks
    private UpdateProductUCImpl updateProductUC;

    @Test
    void updateProduct_success() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO request = ProductDTO.builder()
                .id(1L)
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();

        ProductEntity productEntity = productMapperMock.mapperToEntity(request);
        CategoryEntity categoryEntity = categoryMapperMock.mapperToEntity(request.getCategory());

        when(productRepositoryMock.findById(request.getId())).thenReturn(Optional.ofNullable(productEntity));
        when(categoryRepositoryMock.findById(request.getCategory().getId())).thenReturn(Optional.of(categoryEntity));
        when(productRepositoryMock.save(productEntity)).thenReturn(productEntity);

        // Act
        updateProductUC.updateProduct(request);

        // Assert
        assertEquals(request.getName(), productEntity.getName());
        assertEquals(request.getDescription(), productEntity.getDescription());
        assertEquals(request.getPrice(), productEntity.getPrice());
        assertEquals(request.getCategory().getId(), productEntity.getCategory().getId());
        assertEquals(request.getCategory().getName(), productEntity.getCategory().getName());
    }

    @Test
    void updateProduct_withInvalidProductId_shouldThrowInvalidProductException() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO request = ProductDTO.builder()
                .id(1L)
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();

        when(productRepositoryMock.findById(request.getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidProductException.class, () -> updateProductUC.updateProduct(request));
        verify(categoryRepositoryMock, never()).findById(request.getCategory().getId());
        verify(productRepositoryMock, never()).save(any());
    }
    @Test
    void updateProduct_withInvalidCategoryId_shouldThrowInvalidCategoryException() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO request = ProductDTO.builder()
                .id(1L)
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();
        ProductEntity productEntity = productMapperMock.mapperToEntity(request);
        when(productRepositoryMock.findById(request.getId())).thenReturn(Optional.of(productEntity));
        when(categoryRepositoryMock.findById(request.getCategory().getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidCategoryException.class, () -> updateProductUC.updateProduct(request));

        verify(productRepositoryMock).findById(request.getId());
        verify(categoryRepositoryMock).findById(request.getCategory().getId());
        verify(productRepositoryMock, never()).save(any());
    }
}