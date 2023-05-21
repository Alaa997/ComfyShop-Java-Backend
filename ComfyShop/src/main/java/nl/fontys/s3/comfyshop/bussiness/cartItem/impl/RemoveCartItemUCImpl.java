package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.cartItem.RemoveCartItemUC;
import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RemoveCartItemUCImpl implements RemoveCartItemUC {
    private final CartItemRepository cartItemRepository;
    @Override
    public boolean removeCartItem(Long id) {
        Optional<CartItemEntity> cartItemOptional = cartItemRepository.findById(id);

        if (cartItemOptional.isPresent()) {
            CartItemEntity cartItem = cartItemOptional.get();
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }
}
