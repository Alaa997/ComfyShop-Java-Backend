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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchProductsByNameUCImplTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @InjectMocks
    private SearchProductsByNameUCImpl searchProductsByNameUC;
    @Test
    void searchProductsByName_WhenProductsFound() {
        // Arrange
        String keyword = "i";
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

        when(productRepositoryMock.findByNameContainingIgnoreCase(keyword))
                .thenReturn(productEntityList);

        List<ProductDTO> expected  = ProductMapper.mapperToDTOList(productEntityList);

        // Act
        List<ProductDTO> result = searchProductsByNameUC.searchProductsByName(keyword);

        // Assert
        verify(productRepositoryMock).findByNameContainingIgnoreCase(keyword);
        assertEquals(expected.size(), result.size());
    }


    @Test
    void searchProductsByName_WhenNoProductsFound() {
        // Arrange
        String keyword = "i";
        when(productRepositoryMock.findByNameContainingIgnoreCase(keyword))
                .thenReturn(new ArrayList<>());

        // Act
        List<ProductDTO> result = searchProductsByNameUC.searchProductsByName(keyword);

        verify(productRepositoryMock).findByNameContainingIgnoreCase(keyword);

        // Assert
        assertEquals(0, result.size());
    }
}