//package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;
//
//import nl.fontys.s3.comfyshop.dto.CategoryDTO;
//import nl.fontys.s3.comfyshop.dto.ProductDTO;
//import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;
//import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
//import nl.fontys.s3.comfyshop.dto.user.UserDTO;
//import nl.fontys.s3.comfyshop.mappers.CartItemMapper;
//import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
//import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class AddToCartUCImplTest {
//    @Mock
//    private CartItemRepository cartItemRepositoryMock;
//
//    @Mock
//    private CartItemMapper cartItemMapperMock;
//    @InjectMocks
//    private AddToCartUCImpl addToCartUCMock;
//
//    @Test
//    void addToCart_ExistingCartItem_ShouldUpdateQuantityAndReturnTrue() {
//        // Arrange
//        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
//        ProductDTO productDTO = ProductDTO.builder()
//                .id(1L)
//                .name("Ribs")
//                .description("Tasty")
//                .price(5.5)
//                .category(categoryDTO)
//                .build();
//        UserDTO userDTO = UserDTO.builder()
//                .id(1L)
//                .email("alaa@gmail.com")
//                .password("alaa")
//                .firstName("Alaa")
//                .lastName("Tarakji")
//                .address("123 Main St")
//                .build();
//
//        ShoppingSessionDTO shoppingSessionEntity = ShoppingSessionDTO.builder()
//                .id(1L)
//                .user(userDTO)
//                .ordered(false)
//                .build();
//
//        CartItemDTO request = CartItemDTO.builder()
//                .shoppingSession(shoppingSessionEntity)
//                .product(productDTO)
//                .quantity(2)
//                .build();
//
//
//        CartItemEntity existingCartItem = cartItemMapperMock.toEntity(request);
//        existingCartItem.setQuantity(3);
//
//        when(cartItemRepositoryMock.findByShoppingSessionIdAndProductId(request.getShoppingSession().getId(),
//                request.getProduct().getId())).thenReturn(Optional.of(existingCartItem));
//        when(cartItemRepositoryMock.save(existingCartItem)).thenReturn(existingCartItem);
//
//        // Act
//        boolean result = addToCartUCMock.addToCart(request);
//
//        // Assert
//        assertTrue(result);
//        assertEquals(5, existingCartItem.getQuantity());
//        verify(cartItemRepositoryMock).findByShoppingSessionIdAndProductId(request.getShoppingSession().getId(),
//                request.getProduct().getId());
//        verify(cartItemRepositoryMock).save(existingCartItem);
//        verifyNoMoreInteractions(cartItemRepositoryMock);
//        verifyNoInteractions(cartItemMapperMock);
//    }
//
//    @Test
//    void addToCart_NewCartItem_ShouldSaveAndReturnTrue() {
//        // Arrange
//        CategoryDTO categoryDTO = CategoryDTO.builder().id(1L).name("Meat").build();
//        ProductDTO productDTO = ProductDTO.builder()
//                .id(1L)
//                .name("Ribs")
//                .description("Tasty")
//                .price(5.5)
//                .category(categoryDTO)
//                .build();
//        UserDTO userDTO = UserDTO.builder()
//                .id(1L)
//                .email("alaa@gmail.com")
//                .password("alaa")
//                .firstName("Alaa")
//                .lastName("Tarakji")
//                .address("123 Main St")
//                .build();
//
//        ShoppingSessionDTO shoppingSessionEntity = ShoppingSessionDTO.builder()
//                .id(1L)
//                .user(userDTO)
//                .ordered(false)
//                .build();
//
//        CartItemDTO request = CartItemDTO.builder()
//                .shoppingSession(shoppingSessionEntity)
//                .product(productDTO)
//                .quantity(2)
//                .build();
//
//        CartItemEntity newCartItem = cartItemMapperMock.toEntity(request);
//
//        when(cartItemRepositoryMock.findByShoppingSessionIdAndProductId(request.getShoppingSession().getId(),
//                request.getProduct().getId())).thenReturn(Optional.empty());
//        when(cartItemMapperMock.toEntity(request)).thenReturn(newCartItem);
//        when(cartItemRepositoryMock.save(newCartItem)).thenReturn(newCartItem);
//
//        // Act
//        boolean result = addToCartUCMock.addToCart(request);
//
//        // Assert
//        assertTrue(result);
//        verify(cartItemRepositoryMock).findByShoppingSessionIdAndProductId(request.getShoppingSession().getId(),
//                request.getProduct().getId());
//        verify(cartItemMapperMock).toEntity(request);
//        verify(cartItemRepositoryMock).save(newCartItem);
//        verifyNoMoreInteractions(cartItemRepositoryMock);
//        verifyNoMoreInteractions(cartItemMapperMock);
//    }
//
//}