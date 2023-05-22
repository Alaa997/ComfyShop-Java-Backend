package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import nl.fontys.s3.comfyshop.mappers.CartItemMapper;
import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RemoveCartItemUCImplTest {
    @Mock
    private CartItemRepository cartItemRepositoryMock;

    @Mock
    private CartItemMapper cartItemMapperMock;
    @InjectMocks
    private RemoveCartItemUCImpl removeCartItemUMock;
    @Test
    public void removeCartItem_ExistingCartItem_ShouldReturnTrue() {
        // Arrange
        Long cartItemId = 1L;
        CartItemEntity cartItemEntity = new CartItemEntity();
        when(cartItemRepositoryMock.findById(anyLong())).thenReturn(Optional.of(cartItemEntity));

        // Act
        boolean result = removeCartItemUMock.removeCartItem(cartItemId);

        // Assert
        assertTrue(result);
    }

    @Test
    public void removeCartItem_NonExistingCartItem_ShouldReturnFalse() {
        // Arrange
        Long cartItemId = 1L;
        when(cartItemRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        boolean result = removeCartItemUMock.removeCartItem(cartItemId);

        // Assert
        assertFalse(result);
    }
}