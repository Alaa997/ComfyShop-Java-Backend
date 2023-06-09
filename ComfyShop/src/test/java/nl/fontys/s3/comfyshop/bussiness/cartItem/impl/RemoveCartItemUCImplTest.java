package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

//    @Test
//    void removeCartItem_NonExistingItem_ShouldReturnFalse() {
//        // Arrange
//        Long cartItemId = 1L;
//
//        when(cartItemRepositoryMock.getById(cartItemId)).thenThrow(EntityNotFoundException.class);
//
//        // Act
//        boolean removed = removeCartItemUC.removeCartItem(cartItemId);
//
//        // Assert
//        assertFalse(removed);
//        verify(cartItemRepositoryMock).getById(cartItemId);
//        verify(cartItemRepositoryMock, never()).delete(any(CartItemEntity.class));
//    }
}