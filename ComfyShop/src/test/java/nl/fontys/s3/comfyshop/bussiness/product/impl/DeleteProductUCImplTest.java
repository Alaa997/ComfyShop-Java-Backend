package nl.fontys.s3.comfyshop.bussiness.product.impl;

import nl.fontys.s3.comfyshop.bussiness.exception.InvalidProductException;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteProductUCImplTest {

    @Mock
    private ProductRepository productRepositoryMock;
    @InjectMocks
    private DeleteProductUCImpl deleteProductUC;
    @Test
    void deleteProduct_success() {
        Long productId = 1L;
        when(productRepositoryMock.existsById(productId)).thenReturn(true);

        // Act
        deleteProductUC.deleteProduct(productId);

        // Assert
        verify(productRepositoryMock).deleteById(productId);
        verify(productRepositoryMock).existsById(productId);
    }
    @Test
    void deleteProduct_shouldThrowException_whenProductIdNotExists() {
        // Arrange
        Long productId = 1L;
        when(productRepositoryMock.existsById(productId)).thenReturn(false);

        // Act and Assert
        assertThrows(InvalidProductException.class, () -> deleteProductUC.deleteProduct(productId));
        verify(productRepositoryMock, never()).deleteById(productId);
    }
}