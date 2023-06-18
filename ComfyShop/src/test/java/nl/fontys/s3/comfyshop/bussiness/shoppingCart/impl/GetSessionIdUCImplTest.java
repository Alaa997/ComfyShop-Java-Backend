package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetSessionIdUCImplTest {
    @Mock
    private ShoppingSessionRepository shoppingRepository;

    @InjectMocks
    private GetSessionIdUCImpl getSessionIdUC;

    @Test
    void getSessionId_ShouldReturnSessionId() {
        Long userId = 1L;
        Long sessionId = 123L;

        when(shoppingRepository.findShoppingSessionIdByUserIdAndNotOrdered(userId))
                .thenReturn(sessionId);

        Long result = getSessionIdUC.GetSessionId(userId);

        assertEquals(sessionId, result);
    }
}