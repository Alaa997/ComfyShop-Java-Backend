package nl.fontys.s3.comfyshop.bussiness.shoppingCart.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.GetOrdersUC;
import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
import nl.fontys.s3.comfyshop.mappers.CartItemMapper;
import nl.fontys.s3.comfyshop.persistence.ShoppingSessionRepository;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetOrdersUCImpl implements GetOrdersUC {
    private final ShoppingSessionRepository shoppingRepository;
    private final UserRepository userRepository;

    @Override
    public List<ShoppingSessionDTO> getOrders(Long userId) {
        UserEntity user = userRepository.getById(userId);
        List<ShoppingSessionEntity> orderEntity = shoppingRepository.getByUserAndOrderedTrueOrderedByIdDesc(user);
        List<ShoppingSessionDTO> ordersDTOs = new ArrayList<>();

        for (ShoppingSessionEntity order : orderEntity) {
            ShoppingSessionDTO orderDTO = new ShoppingSessionDTO();
            orderDTO.setId(order.getId());
            orderDTO.setCartItems(CartItemMapper.toDTOList(order.getCartItems()));
            orderDTO.setOrdered(order.isOrdered());
            ordersDTOs.add(orderDTO);
        }
        return ordersDTOs;
    }
}
