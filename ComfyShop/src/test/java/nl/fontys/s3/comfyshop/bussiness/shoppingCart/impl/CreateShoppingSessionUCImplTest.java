package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
import nl.fontys.s3.comfyshop.mappers.UserMapper;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateShoppingSessionUCImplTest {
    @Mock
    private ShoppingSessionRepository shoppingSessionRepositoryMock;
    @Mock
    private UserMapper userMapperMock;
    @InjectMocks
    private CreateShoppingSessionUCImpl createShoppingSessionUC;

    @Test
    void createShoppingSession() {
        // Arrange
        UserEntity request = UserEntity.builder()
                .id(1L)
                .email("alaa@gmail.com")
                .password("alaa")
                .firstName("Alaa")
                .lastName("Tarakji")
                .address("123 Main St")
                .build();

        ShoppingSessionEntity savedShoppingSessionEntity = ShoppingSessionEntity.builder()
                .id(1L)
                .user(request)
                .build();
        ShoppingSessionDTO expectedShoppingSessionDTO = ShoppingSessionDTO.builder()
                .id(1L)
                .user(UserMapper.mapperToDTO(request))
                .build();
        when(shoppingSessionRepositoryMock.save(ShoppingSessionEntity.builder()
                .user(request)
                .build())).
                thenReturn(savedShoppingSessionEntity);
        // Act
        ShoppingSessionDTO result = createShoppingSessionUC.createShoppingSession(request);

        // Assert
        assertNotNull(result);
        assertEquals(expectedShoppingSessionDTO, result);
    }
}