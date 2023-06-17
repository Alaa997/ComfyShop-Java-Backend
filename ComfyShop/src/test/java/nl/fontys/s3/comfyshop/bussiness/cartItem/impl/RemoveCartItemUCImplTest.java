package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveCartItemUCImplTest {
    @Mock
    private CartItemRepository cartItemRepositoryMock;

    @InjectMocks
    private RemoveCartItemUCImpl removeCartItemUC;

    @Test
    public void testRemoveCartItem_Success() {
        // Arrange
        Long id = 1L;

        // Act
        boolean result = removeCartItemUC.removeCartItem(id);

        // Assert
        assertTrue(result);
        verify(cartItemRepositoryMock, times(1)).deleteById(id);
    }

    @Test
    void removeCartItem_ItemDoesNotExist_ReturnsFalse() {
        // Arrange
        Long cartItemId = 1L;
        doThrow(EntityNotFoundException.class).when(cartItemRepositoryMock).deleteById(cartItemId);
        // Act
        boolean result = removeCartItemUC.removeCartItem(cartItemId);

        // Assert
        assertFalse(result);
        verify(cartItemRepositoryMock, times(1)).deleteById(cartItemId);
    }
}