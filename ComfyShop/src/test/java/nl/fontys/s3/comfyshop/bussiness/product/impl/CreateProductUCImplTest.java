package nl.fontys.s3.comfyshop.bussiness.product.impl;

import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCategoryException;
import nl.fontys.s3.comfyshop.bussiness.exception.NameAlreadyExistsException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProductUCImplTest {

    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private CategoryRepository categoryRepositoryMock;
    @Mock
    private ProductMapper productMapperMock;
    @Mock
    private CategoryMapper categoryMapperMock;
    @InjectMocks
    private CreateProductUCImpl createProductUC;

    @Test
    void createProduct_Success() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO request = ProductDTO.builder()
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();

        CategoryEntity categoryEntity = categoryMapperMock.mapperToEntity(categoryDTO);

        when(productRepositoryMock.existsByName(request.getName())).thenReturn(false);
        when(categoryRepositoryMock.findById(request.getCategory().getId())).thenReturn(Optional.of(categoryEntity));

        ProductEntity productEntity = productMapperMock.mapperToEntity(request);
        when(productRepositoryMock.save(any(ProductEntity.class))).thenReturn(productEntity);

        // Act
        ProductDTO result = createProductUC.createProduct(request);

        // Assert
        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
        assertEquals(request.getCategory().getId(), result.getCategory().getId());
    }

    @Test
    void createProduct_WithExistingName_Exception() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO request = ProductDTO.builder()
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();

        when(productRepositoryMock.existsByName(request.getName())).thenReturn(true);

        // Act and Assert
        assertThrows(NameAlreadyExistsException.class, () -> createProductUC.createProduct(request));
        verify(categoryRepositoryMock, never()).findById(request.getCategory().getId());
        verify(productRepositoryMock, never()).save(productMapperMock.mapperToEntity(request));
    }

    @Test
    void createProduct_invalidCategoryId() {
        // Arrange
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO request = ProductDTO.builder()
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();

        when(productRepositoryMock.existsByName(request.getName())).thenReturn(false);
        when(categoryRepositoryMock.findById(request.getCategory().getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidCategoryException.class, () -> createProductUC.createProduct(request));
        verify(productRepositoryMock, times(1)).existsByName(request.getName());
        verify(productRepositoryMock, never()).save(productMapperMock.mapperToEntity(request));
    }
}