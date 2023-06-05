package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import nl.fontys.s3.comfyshop.bussiness.exception.InvalidShoppingCartException;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidUserException;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateShoppingSessionUCImplTest {
    @Mock
    private ShoppingSessionRepository shoppingSessionRepositoryMock;
    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private UpdateShoppingSessionUCImpl updateShoppingSessionUC;


    @Test
    void updateShoppingSession_ValidSessionId_ShouldReturnTrue() {
        // Arrange
        Long shoppingSessionId = 1L;
        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("alaa@gmail.com")
                .password("alaa")
                .firstName("Alaa")
                .lastName("Tarakji")
                .address("123 Main St")
                .build();

        ShoppingSessionEntity sessionEntity = ShoppingSessionEntity.builder()
                .id(1L)
                .user(user)
                .build();

        when(shoppingSessionRepositoryMock.findById(shoppingSessionId)).thenReturn(Optional.of(sessionEntity));
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));

        when(shoppingSessionRepositoryMock.save(sessionEntity)).thenReturn(sessionEntity);

        // Act
        boolean result = updateShoppingSessionUC.UpdateShoppingSession(shoppingSessionId, user.getId());

        // Assert
        assertTrue(result);
        assertTrue(sessionEntity.isOrdered());
        verify(shoppingSessionRepositoryMock).findById(shoppingSessionId);
        verify(shoppingSessionRepositoryMock).save(sessionEntity);
    }

    @Test
    void updateShoppingSession_InvalidSessionId_ShouldThrowException() {
        // Arrange
        Long shoppingSessionId = 1L;

        when(shoppingSessionRepositoryMock.findById(shoppingSessionId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidShoppingCartException.class, () ->
                updateShoppingSessionUC.UpdateShoppingSession(shoppingSessionId, 1L));
        verify(shoppingSessionRepositoryMock).findById(shoppingSessionId);
        verifyNoMoreInteractions(shoppingSessionRepositoryMock);
    }
    @Test
    void updateShoppingSession_InvalidUserId_ShouldThrowException() {
        // Arrange
        Long shoppingSessionId = 1L;
        Long userId = 1L;

        ShoppingSessionEntity sessionEntity = ShoppingSessionEntity.builder()
                .id(shoppingSessionId)
                .build();

        when(shoppingSessionRepositoryMock.findById(shoppingSessionId)).thenReturn(Optional.of(sessionEntity));
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidUserException.class, () ->
                updateShoppingSessionUC.UpdateShoppingSession(shoppingSessionId, userId));

        verify(shoppingSessionRepositoryMock).findById(shoppingSessionId);
        verify(userRepositoryMock).findById(userId);
        verifyNoMoreInteractions(shoppingSessionRepositoryMock, userRepositoryMock);
    }

}