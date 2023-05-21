package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.cartItem.UpdateCartItemUC;
import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;
import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCartItemUCImpl implements UpdateCartItemUC {
    private final CartItemRepository cartItemRepository;
    @Override
    public boolean updateCartItem(CartItemDTO request) {
        Optional<CartItemEntity> cartItemOptional = cartItemRepository.findById(request.getId());

        if (cartItemOptional.isPresent()) {
            CartItemEntity cartItem = cartItemOptional.get();
            int updatedQuantity = request.getQuantity();

            if (updatedQuantity >= 0) {
                cartItem.setQuantity(updatedQuantity);
                cartItemRepository.save(cartItem);
                return true;
            }
        }

        return false;
    }
}
