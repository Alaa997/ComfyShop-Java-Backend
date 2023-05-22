package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import nl.fontys.s3.comfyshop.bussiness.exception.InvalidUserException;
import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
import nl.fontys.s3.comfyshop.mappers.ShoppingSessionMapper;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetShoppingSessionUCImplTest {
    @Mock
    private ShoppingSessionRepository shoppingSessionRepositoryMock;
    @Mock
    private ShoppingSessionMapper shoppingSessionMapperMock;
    @InjectMocks
    private GetShoppingSessionUCImpl getShoppingSessionUC;

    @Test
    void getShoppingSession() {
        // Arrange
        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("alaa@gmail.com")
                .password("alaa")
                .firstName("Alaa")
                .lastName("Tarakji")
                .address("123 Main St")
                .build();

        ShoppingSessionEntity shoppingSessionEntity = ShoppingSessionEntity.builder()
                .id(1L)
                .user(user)
                .build();
        ShoppingSessionDTO expected  = shoppingSessionMapperMock.toDTO(shoppingSessionEntity);
        when(shoppingSessionRepositoryMock.findByUserAndOrderedFalse(user)).thenReturn(Optional.of(shoppingSessionEntity));

        // Act
        ShoppingSessionDTO result = getShoppingSessionUC.getShoppingSession(user);

        // Arrange
        assertNotNull(result);
        assertEquals(expected, result);
    }
    @Test
    void getShoppingSession_NonExistingSession_ShouldThrowException() {
        // Arrange
        UserEntity user = new UserEntity();

        when(shoppingSessionRepositoryMock.findByUserAndOrderedFalse(user)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidUserException.class, () -> getShoppingSessionUC.getShoppingSession(user));

        // Verify
        verify(shoppingSessionRepositoryMock).findByUserAndOrderedFalse(user);
        verifyNoInteractions(shoppingSessionMapperMock);
    }
}