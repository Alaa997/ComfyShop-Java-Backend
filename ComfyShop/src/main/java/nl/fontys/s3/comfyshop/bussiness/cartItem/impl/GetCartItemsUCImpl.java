package nl.fontys.s3.comfyshop.bussiness.cartItem.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.cartItem.GetCartItemsUC;
import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
import nl.fontys.s3.comfyshop.persistence.CartItemRepository;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class GetCartItemsUCImpl implements GetCartItemsUC {
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItemDTO> getCartItems(Long sessionId) {
        List<CartItemEntity> cartItemEntities = cartItemRepository.findByShoppingSessionId(sessionId);
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();

        for (CartItemEntity cartItemEntity : cartItemEntities) {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartItemEntity.getId());
            cartItemDTO.setProduct(ProductMapper.mapperToDTO(cartItemEntity.getProduct()));
            cartItemDTO.setQuantity(cartItemEntity.getQuantity());

            cartItemDTOs.add(cartItemDTO);
        }

        if (cartItemEntities.isEmpty()) {
            return Collections.emptyList();
        }
        return cartItemDTOs;
    }
}
