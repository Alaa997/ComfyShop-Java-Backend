package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidShoppingCartException;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidUserException;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.UpdateShoppingSessionUC;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateShoppingSessionUCImpl implements UpdateShoppingSessionUC {
    private final ShoppingSessionRepository shoppingRepository;
    private final UserRepository userRepository;
    @Override
    public boolean UpdateShoppingSession(Long shoppingSessionId, Long userId) {

        Optional<ShoppingSessionEntity> optionalShoppingSession = shoppingRepository.findById(shoppingSessionId);
        if(!optionalShoppingSession.isPresent())
            throw new InvalidShoppingCartException("SHOPPING_SESSION_ID_INVALID");

        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent())
            throw new InvalidUserException("USER_ID_INVALID");

        ShoppingSessionEntity sessionEntity = optionalShoppingSession.get();
        sessionEntity.setOrdered(true);
        shoppingRepository.save(sessionEntity);

        shoppingRepository.save(ShoppingSessionEntity.builder().user(optionalUser.get()).ordered(false).build());
        return true;
    }
}
