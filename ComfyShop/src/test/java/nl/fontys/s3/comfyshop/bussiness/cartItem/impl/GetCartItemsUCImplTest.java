package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;
import nl.fontys.s3.comfyshop.mappers.CartItemMapper;
import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class GetCartItemsUCImplTest {
    @Mock
    private CartItemRepository cartItemRepositoryMock;

    @Mock
    private CartItemMapper cartItemMapperMock;

    @InjectMocks
    private GetCartItemsUCImpl getCartItemsUC;

    @Test
    void getCartItems_ExistingCartItems_ShouldReturnCartItemDTOList() {
        // Arrange
        Long sessionId = 1L;
        CategoryEntity category = CategoryEntity.builder().id(1L).name("Meat").build();
        ProductEntity product = ProductEntity.builder()
                .id(1L)
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(category)
                .build();

        CartItemEntity cartItem1 = CartItemEntity.builder()
                .id(1L)
                .product(product)
                .quantity(2)
                .build();

        CategoryEntity category2 = CategoryEntity.builder().id(1L).name("Meat").build();
        ProductEntity product2 = ProductEntity.builder()
                .id(2L)
                .name("Steak")
                .description("Juicy")
                .price(8.5)
                .category(category2)
                .build();

        CartItemEntity cartItem2 = CartItemEntity.builder()
                .id(2L)
                .product(product2)
                .quantity(4)
                .build();

        List<CartItemEntity> cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        List<CartItemDTO> expected = cartItemMapperMock.toDTOList(cartItems);

        when(cartItemRepositoryMock.findByShoppingSessionId(sessionId)).thenReturn(cartItems);

        // Act
        List<CartItemDTO> result = getCartItemsUC.getCartItems(sessionId);

        // Assert
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
        assertEquals(expected.get(0).getId(), result.get(0).getId());
        assertEquals(expected.get(0).getProduct().getName(), result.get(0).getProduct().getName());

        verify(cartItemRepositoryMock).findByShoppingSessionId(sessionId);
    }

    @Test
    void getCartItems_NoCartItems_ShouldReturnEmptyList() {
        // Arrange
        Long sessionId = 1L;

        when(cartItemRepositoryMock.findByShoppingSessionId(sessionId)).thenReturn(Collections.emptyList());

        // Act
        List<CartItemDTO> result = getCartItemsUC.getCartItems(sessionId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(cartItemRepositoryMock).findByShoppingSessionId(sessionId);
        verifyNoMoreInteractions(cartItemRepositoryMock);
        verifyNoInteractions(cartItemMapperMock);
    }
}

