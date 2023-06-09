package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import nl.fontys.s3.comfyshop.dto.CategoryDTO;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;
import nl.fontys.s3.comfyshop.mappers.CartItemMapper;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddToCartUCImplTest {
    @Mock
    private CartItemRepository cartItemRepositoryMock;
    @Mock
    private ShoppingSessionRepository shoppingSessionRepositoryMock;
    @InjectMocks
    private AddToCartUCImpl addToCartUC;

    @Test
    void addToCart_ExistingCartItem_ShouldUpdateQuantityAndReturnTrue() {
        // Arrange
        Long shoppingSessionId = 1L;
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();

        CartItemDTO request = CartItemDTO.builder()
                .id(1L)
                .sessionId(shoppingSessionId)
                .product(productDTO)
                .quantity(2)
                .build();

        CartItemEntity existingCartItem = CartItemMapper.toEntity(request);
        existingCartItem.setQuantity(3);

        when(cartItemRepositoryMock.findByShoppingSessionIdAndProductId(shoppingSessionId,
                request.getProduct().getId())).thenReturn(Optional.of(existingCartItem));
        when(cartItemRepositoryMock.save(existingCartItem)).thenReturn(existingCartItem);

        // Act
        boolean result = addToCartUC.addToCart(request);

        // Assert
        assertTrue(result);
        assertEquals(5, existingCartItem.getQuantity());
        verify(cartItemRepositoryMock).findByShoppingSessionIdAndProductId(shoppingSessionId,
                request.getProduct().getId());
    }

    @Test
    void addToCart_NewCartItem_ShouldSaveAndReturnTrue() {
        // Arrange
        Long shoppingSessionId = 1L;
        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
        ProductDTO productDTO = ProductDTO.builder()
                .id(1L)
                .name("Ribs")
                .description("Tasty")
                .price(5.5)
                .category(categoryDTO)
                .build();

        CartItemDTO request = CartItemDTO.builder()
                .sessionId(shoppingSessionId)
                .product(productDTO)
                .quantity(1)
                .build();

        ShoppingSessionEntity sessionEntity = ShoppingSessionEntity.builder()
                .id(1L)
                .ordered(false)
                .build();

        CartItemEntity newCartItem = CartItemMapper.toEntity(request);

        CartItemEntity savedCartItem = CartItemEntity.builder()
                .id(1L)
                .shoppingSession(sessionEntity)
                .product(ProductMapper.mapperToEntity( productDTO))
                .quantity(1)
                .build();

        when(cartItemRepositoryMock.findByShoppingSessionIdAndProductId(shoppingSessionId,
                request.getProduct().getId())).thenReturn(Optional.empty());
        when(cartItemRepositoryMock.save(newCartItem)).thenReturn(savedCartItem);

        // Act
        boolean result = addToCartUC.addToCart(request);

        // Assert
        assertTrue(result);
        assertEquals(1,newCartItem.getQuantity());
        verify(cartItemRepositoryMock).findByShoppingSessionIdAndProductId(shoppingSessionId,
                request.getProduct().getId());
        verify(cartItemRepositoryMock).save(newCartItem);
        verifyNoMoreInteractions(cartItemRepositoryMock);
    }

}