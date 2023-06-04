//package nl.fontys.s3.comfyshop.bussiness.order.impl;
//
//import nl.fontys.s3.comfyshop.persistence.OrderRepository;
//import nl.fontys.s3.comfyshop.persistence.UserRepository;
//import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
//import nl.fontys.s3.comfyshop.persistence.entity.shopping.OrderDetailsEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class CreateOrderUCImplTest {
//
//    @Mock
//    private OrderRepository orderRepositoryMock;
//
//    @Mock
//    private UserRepository userRepositoryMock;
//
//    @InjectMocks
//    private CreateOrderUCImpl createOrderUC;
//
//    @Test
//    void createOrder() {
//        // Arrange
//        Long userId = 123L;
//        UserEntity userEntity = new UserEntity();
//        when(userRepositoryMock.getById(userId)).thenReturn(userEntity);
//
//        // Act
//        createOrderUC.createOrder(userId);
//
//        // Assert
//        verify(userRepositoryMock).getById(userId);
//        verify(orderRepositoryMock).save(any(OrderDetailsEntity.class));
//    }
//}