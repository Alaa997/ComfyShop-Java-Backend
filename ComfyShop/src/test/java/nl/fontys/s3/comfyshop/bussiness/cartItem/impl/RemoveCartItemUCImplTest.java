package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
    @Captor
    private ArgumentCaptor<CartItemEntity> cartItemCaptor;

    @Test
    void removeCartItem_ExistingItem_ShouldReturnTrue() {
        // Arrange
        Long cartItemId = 1L;
        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setId(cartItemId);

        when(cartItemRepositoryMock.getById(cartItemId)).thenReturn(cartItem);

        // Act
        boolean removed = removeCartItemUC.removeCartItem(cartItemId);

        // Assert
        assertTrue(removed);
        verify(cartItemRepositoryMock).getById(cartItemId);
        verify(cartItemRepositoryMock).delete(cartItemCaptor.capture());
        assertEquals(cartItem, cartItemCaptor.getValue());
    }

    @Test
    void removeCartItem_NonExistingItem_ShouldReturnFalse() {
        // Arrange
        Long cartItemId = 1L;

        when(cartItemRepositoryMock.getById(cartItemId)).thenThrow(EntityNotFoundException.class);

        // Act
        boolean removed = removeCartItemUC.removeCartItem(cartItemId);

        // Assert
        assertFalse(removed);
        verify(cartItemRepositoryMock).getById(cartItemId);
        verify(cartItemRepositoryMock, never()).delete(any(CartItemEntity.class));
    }
}