package nl.fontys.s3.comfyshop.bussiness.product.impl;

import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class GetAllProductsUCImplTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @Mock
    private ProductMapper productMapperMock;
    @InjectMocks
    private GetAllProductsUCImpl getAllProductsUC;

    @Test
    void getAllProductsUC() {
        // Arrange
        CategoryEntity categoryEntity = CategoryEntity.builder().id(1L).name("Meat").build();
        CategoryEntity categoryEntity2 = CategoryEntity.builder().id(2L).name("Chicken").build();
        ProductEntity product1 = ProductEntity.builder()
                .id(1L)
                .name("Ribs1")
                .description("Tasty1")
                .price(5.5)
                .category(categoryEntity)
                .build();
        ProductEntity product2 = ProductEntity.builder()
                .id(2L)
                .name("Chicken")
                .description("Chicken")
                .price(7.5)
                .category(categoryEntity2)
                .build();
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(product1);
        productEntityList.add(product2);

        when(productRepositoryMock.findAll()).thenReturn(productEntityList);

        List<ProductDTO> expected = productMapperMock.mapperToDTOList(productEntityList);

        // Act
        List<ProductDTO> actualResult = getAllProductsUC.getAllProducts();

        // Assert
        assertEquals(expected.size(), actualResult.size());
        assertEquals(expected.get(0).getName(), actualResult.get(0).getName());
        assertEquals(expected.get(1).getName(), actualResult.get(1).getName());
    }
}