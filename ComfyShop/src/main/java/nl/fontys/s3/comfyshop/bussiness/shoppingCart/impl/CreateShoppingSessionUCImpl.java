package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.CreateShoppingSessionUC;
import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
import nl.fontys.s3.comfyshop.mappers.ShoppingSessionMapper;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateShoppingSessionUCImpl implements CreateShoppingSessionUC {
    private final ShoppingSessionRepository shoppingRepository;

    @Override
    public ShoppingSessionDTO createShoppingSession(UserEntity user) {
        ShoppingSessionEntity savedShoppingCart = shoppingRepository.save(ShoppingSessionEntity.builder().user(user).build());
        return ShoppingSessionMapper.toDTO(savedShoppingCart);
    }
}
