package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidUserException;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.GetShoppingSessionUC;
import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
import nl.fontys.s3.comfyshop.mappers.ShoppingSessionMapper;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetShoppingSessionUCImpl implements GetShoppingSessionUC {
    private final ShoppingSessionRepository shoppingRepository;
    @Override
    public ShoppingSessionDTO getShoppingSession(UserEntity user) {
       Optional<ShoppingSessionEntity> optionalShoppingSession = shoppingRepository.findByUserAndOrderedFalse(user);
        if(!optionalShoppingSession.isPresent())
            throw new InvalidUserException("User_ID_INVALID"); // to be done
        return ShoppingSessionMapper.toDTO(optionalShoppingSession.get());
    }
}
