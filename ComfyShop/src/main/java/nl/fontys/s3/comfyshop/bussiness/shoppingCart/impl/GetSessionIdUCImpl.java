package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.GetSessionIdUC;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetSessionIdUCImpl implements GetSessionIdUC {
    private final ShoppingSessionRepository shoppingRepository;
    @Override
    public Long GetSessionId(Long userId) {
        return shoppingRepository.findShoppingSessionIdByUserIdAndNotOrdered(userId);
    }
}
