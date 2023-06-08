package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.cartItem.RemoveCartItemUC;
import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class RemoveCartItemUCImpl implements RemoveCartItemUC {
    private final CartItemRepository cartItemRepository;
    @Override
    public boolean removeCartItem(Long id) {
        try {
            cartItemRepository.deleteById(id);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }
}
