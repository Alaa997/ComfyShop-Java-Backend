package nl.fontys.s3.comfyshop.bussiness.user.impl;

import nl.fontys.s3.comfyshop.bussiness.exception.UserNotFoundException;
import nl.fontys.s3.comfyshop.dto.user.UserDTO;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCurrentUserUCImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private GetCurrentUserUCImpl getCurrentUserUC;

    @Test
    void getCurrentUser_ExistingUser_ShouldReturnUserDTO() {
        // Arrange
        String email = "alaa@gmail.com";
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email(email)
                .firstName("John")
                .lastName("Doe")
                .build();

        when(userRepositoryMock.findByEmail(email)).thenReturn(Optional.of(userEntity));

        // Act
        UserDTO result = getCurrentUserUC.getCurrentUser(email);

        // Assert
        assertNotNull(result);
        assertEquals(userEntity.getId(), result.getId());
        assertEquals(userEntity.getEmail(), result.getEmail());
        assertEquals(userEntity.getFirstName(), result.getFirstName());
        assertEquals(userEntity.getLastName(), result.getLastName());
        verify(userRepositoryMock).findByEmail(email);
    }

    @Test
    void getCurrentUser_NonExistingUser_ShouldThrowUserNotFoundException() {
        // Arrange
        String email = "notexist@gmail.com";
        when(userRepositoryMock.findByEmail(email)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> getCurrentUserUC.getCurrentUser(email));
        verify(userRepositoryMock).findByEmail(email);
    }
}