package nl.fontys.s3.comfyshop.bussiness.product.impl;

import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidProductException;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProductUCImplTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private CategoryRepository categoryRepositoryMock;
    @Mock
    private ProductMapper productMapperMock;
    @InjectMocks
    private GetProductUCImpl getProductUC;

    @Test
    void getProduct_success() {
        // Arrange
        Long productId = 1L;
        CategoryEntity categoryEntity = CategoryEntity.builder().id(1L).name("Meat").build();
        ProductEntity productEntity = ProductEntity.builder()
                .id(productId)
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryEntity)
                .build();

        when(productRepositoryMock.findById(productId)).thenReturn(Optional.of(productEntity));
        ProductDTO expected = productMapperMock.mapperToDTO(productEntity);

        // Act
        Optional<ProductDTO> actual = getProductUC.getProduct(productId);

        // Assert
        assertEquals(expected, actual.get());
    }
    @Test
    void getProducts_invalidProductId() {
        // Arrange
        Long productId = 1L;

        when(productRepositoryMock.findById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidProductException.class, () -> getProductUC.getProduct(productId));
    }
}