package nl.fontys.s3.comfyshop.bussiness.cart.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidUserException;
import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
import nl.fontys.s3.comfyshop.mappers.ShoppingSessionMapper;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateShoppingCartUCImpl implements nl.fontys.s3.comfyshop.bussiness.cart.CreateShoppingCartUCImpl {

    private final ShoppingSessionRepository shoppingRepository;
    private final UserRepository userRepository;

    @Override
    public ShoppingSessionDTO createShoppingCart(ShoppingSessionDTO request) {
        Optional<UserEntity> optionalUser = userRepository.findById(request.getUser().getId());
        if (!optionalUser.isPresent())
            throw new InvalidUserException("USER_ID_INVALID");

        ShoppingSessionEntity savedShoppingCart = shoppingRepository.save(ShoppingSessionMapper.toEntity(request));
        return ShoppingSessionMapper.toDTO(savedShoppingCart);
    }
}
