package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.cartItem.AddToCartUC;
import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;
import nl.fontys.s3.comfyshop.mappers.CartItemMapper;
import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddToCartUCImpl implements AddToCartUC {
    private final CartItemRepository cartItemRepository;
    private final ShoppingSessionRepository shoppingSessionRepository;

    @Override
    public boolean addToCart(CartItemDTO request) {
        Optional<CartItemEntity> existingCartItemOptional =
                cartItemRepository.findByShoppingSessionIdAndProductId(
                request.getSessionId(), request.getProduct().getId());

        if (existingCartItemOptional.isPresent()) {
            CartItemEntity existingCartItem = existingCartItemOptional.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            cartItemRepository.save(existingCartItem);
        } else {
            ShoppingSessionEntity sessionEntity = shoppingSessionRepository.getById(request.getSessionId());
            CartItemEntity newCartItem = CartItemMapper.toEntity(request);
            newCartItem.setShoppingSession(sessionEntity);
            cartItemRepository.save(newCartItem);
        }

        return true;
    }
}
