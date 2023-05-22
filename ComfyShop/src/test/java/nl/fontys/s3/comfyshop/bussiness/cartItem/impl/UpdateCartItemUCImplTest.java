package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCartItemUCImplTest {

    @Mock
    private CartItemRepository cartItemRepositoryMock;

    @InjectMocks
    private UpdateCartItemUCImpl updateCartItemUC;

    @Test
    public void updateCartItem_ExistingCartItemWithValidQuantity_ShouldReturnTrue() {
        // Arrange
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(1L);
        cartItemDTO.setQuantity(5);
        CartItemEntity cartItemEntity = new CartItemEntity();
        when(cartItemRepositoryMock.findById(anyLong())).thenReturn(Optional.of(cartItemEntity));

        // Act
        boolean result = updateCartItemUC.updateCartItem(cartItemDTO);

        // Assert
        assertTrue(result);
        verify(cartItemRepositoryMock).save(cartItemEntity);
    }

    @Test
    public void updateCartItem_ExistingCartItemWithNegativeQuantity_ShouldReturnFalse() {
        // Arrange
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(1L);
        cartItemDTO.setQuantity(-5);
        CartItemEntity cartItemEntity = new CartItemEntity();
        when(cartItemRepositoryMock.findById(anyLong())).thenReturn(Optional.of(cartItemEntity));

        // Act
        boolean result = updateCartItemUC.updateCartItem(cartItemDTO);

        // Assert
        assertFalse(result);
    }

    @Test
    public void updateCartItem_NonExistingCartItem_ShouldReturnFalse() {
        // Arrange
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(1L);
        cartItemDTO.setQuantity(5);
        when(cartItemRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        boolean result = updateCartItemUC.updateCartItem(cartItemDTO);

        // Assert
        assertFalse(result);
    }
}