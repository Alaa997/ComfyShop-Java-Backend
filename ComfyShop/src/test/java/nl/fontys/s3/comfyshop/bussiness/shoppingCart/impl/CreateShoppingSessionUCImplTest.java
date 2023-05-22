package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CreateShoppingSessionUCImplTest {
//    @Mock
//    private ShoppingSessionRepository shoppingSessionRepositoryMock;
//    @Mock
//    private UserMapper userMapperMock;
//    @InjectMocks
//    private CreateShoppingSessionUCImpl createShoppingSessionUC;
//
//    @Test
//    void createShoppingSession() {
//        // Arrange
//        UserEntity request = UserEntity.builder()
//                .id(1L)
//                .email("alaa@gmail.com")
//                .password("alaa")
//                .firstName("Alaa")
//                .lastName("Tarakji")
//                .address("123 Main St")
//                .build();
//
//        ShoppingSessionEntity savedShoppingSessionEntity = ShoppingSessionEntity.builder()
//                .id(1L)
//                .user(request)
//                .build();
//        ShoppingSessionEntity expectedShoppingSessionEntity = ShoppingSessionEntity.builder()
//                .id(1L)
//                .user(request)
//                .build();
//        when(shoppingSessionRepositoryMock.save(ShoppingSessionEntity.builder()
//                .id(1L)
//                .user(request)
//                .build())).
//                thenReturn(savedShoppingSessionEntity);
//        // Act
//        ShoppingSessionDTO result = createShoppingSessionUC.createShoppingSession(request);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(expectedShoppingSessionEntity, result);
//    }
}