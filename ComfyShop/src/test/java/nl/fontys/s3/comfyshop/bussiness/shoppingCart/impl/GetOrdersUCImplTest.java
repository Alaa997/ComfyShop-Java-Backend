package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrdersUCImplTest {
    @Mock
    private ShoppingSessionRepository shoppingSessionRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private GetOrdersUCImpl getOrdersUC;
    @Test
    void getOrders_ValidUserId_ShouldReturnOrdersDTOs() {
        // Arrange
        Long userId = 1L;
        UserEntity user = UserEntity.builder()
                .id(userId)
                .email("user@gmail.com")
                .build();

        ShoppingSessionEntity orderEntity = ShoppingSessionEntity.builder()
                .id(1L)
                .user(user)
                .ordered(true)
                .build();

        when(userRepositoryMock.getById(userId)).thenReturn(user);
        when(shoppingSessionRepositoryMock.getByUserAndOrderedTrueOrderedByIdDesc(user)).thenReturn(Collections.singletonList(orderEntity));

        // Act
        List<ShoppingSessionDTO> result = getOrdersUC.getOrders(userId);

        // Assert
        assertEquals(1, result.size());
        ShoppingSessionDTO orderDTO = result.get(0);
        assertEquals(orderEntity.getId(), orderDTO.getId());
        assertEquals(orderEntity.isOrdered(), orderDTO.isOrdered());

        verify(userRepositoryMock).getById(userId);
        verify(shoppingSessionRepositoryMock).getByUserAndOrderedTrueOrderedByIdDesc(user);
    }
}