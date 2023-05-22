//package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;
//
//import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;
//import nl.fontys.s3.comfyshop.mappers.CartItemMapper;
//import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
//import nl.fontys.s3.comfyshop.persistence.entity.CategoryEntity;
//import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
//import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
//import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
//import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class GetCartItemsUCImplTest {
//    @Mock
//    private CartItemRepository cartItemRepositoryMock;
//
//    @Mock
//    private CartItemMapper cartItemMapperMock;
//    @InjectMocks
//    private GetCartItemsUCImpl getCartItemsUC;
//    @Test
//    void getCartItems_ExistingCartItems_ShouldReturnCartItemDTOList() {
//        // Arrange
//        Long sessionId = 1L;
//        CategoryEntity category = CategoryEntity.builder().id(1L).name("Meat").build();
//        ProductEntity product = ProductEntity.builder()
//                .id(1L)
//                .name("Ribs")
//                .description("Tasty")
//                .price(5.5)
//                .category(category)
//                .build();
//        UserEntity user = UserEntity.builder()
//                .id(1L)
//                .email("alaa@gmail.com")
//                .password("alaa")
//                .firstName("Alaa")
//                .lastName("Tarakji")
//                .address("123 Main St")
//                .build();
//
//        ShoppingSessionEntity shoppingSessionEntity = ShoppingSessionEntity.builder()
//                .id(1L)
//                .user(user)
//                .ordered(false)
//                .build();
//
//        CartItemEntity cartItem = CartItemEntity.builder()
//                .id(1L)
//                .shoppingSession(shoppingSessionEntity)
//                .product(product)
//                .quantity(2)
//                .build();
//
//        CategoryEntity category2 = CategoryEntity.builder().id(1L).name("Meat").build();
//        ProductEntity product2 = ProductEntity.builder()
//                .id(1L)
//                .name("Ribs")
//                .description("Tasty")
//                .price(5.5)
//                .category(category2)
//                .build();
//        UserEntity user2 = UserEntity.builder()
//                .id(1L)
//                .email("alaa@gmail.com")
//                .password("alaa")
//                .firstName("Alaa")
//                .lastName("Tarakji")
//                .address("123 Main St")
//                .build();
//
//        ShoppingSessionEntity shoppingSessionEntity2 = ShoppingSessionEntity.builder()
//                .id(1L)
//                .user(user2)
//                .ordered(false)
//                .build();
//
//        CartItemEntity cartItem2 = CartItemEntity.builder()
//                .id(2L)
//                .shoppingSession(shoppingSessionEntity2)
//                .product(product2)
//                .quantity(4)
//                .build();
//
//        List<CartItemEntity> cartItems = new ArrayList<>(Arrays.asList(cartItem, cartItem2));
//        List<CartItemDTO> expected = cartItemMapperMock.toDTOList(cartItems);
//
//        when(cartItemRepositoryMock.findByShoppingSessionId(anyLong())).thenReturn(cartItems);
//        when(cartItemMapperMock.toDTOList(cartItems)).thenReturn(expected);
//
//        // Act
//        List<CartItemDTO> result = getCartItemsUC.getCartItems(sessionId);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        verify(cartItemRepositoryMock).findByShoppingSessionId(sessionId);
//        verify(cartItemMapperMock).toDTOList(cartItems);
//        verifyNoMoreInteractions(cartItemRepositoryMock);
//        verifyNoMoreInteractions(cartItemMapperMock);
//    }
//
//
//    @Test
//    void getCartItems_NoCartItems_ShouldReturnEmptyList() {
//        // Arrange
//        Long sessionId = 1L;
//
//        when(cartItemRepositoryMock.findByShoppingSessionId(sessionId)).thenReturn(Collections.emptyList());
//
//        // Act
//        List<CartItemDTO> result = getCartItemsUC.getCartItems(sessionId);
//
//        // Assert
//        assertNotNull(result);
//        assertTrue(result.isEmpty());
//        verify(cartItemRepositoryMock).findByShoppingSessionId(sessionId);
//        verifyNoMoreInteractions(cartItemRepositoryMock);
//        verifyNoInteractions(cartItemMapperMock);
//    }
//}