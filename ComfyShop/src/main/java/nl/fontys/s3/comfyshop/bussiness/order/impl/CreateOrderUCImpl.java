package nl.fontys.s3.comfyshop.bussiness.order.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.order.CreateOrderUC;
import nl.fontys.s3.comfyshop.persistence.OrderItemsRepository;
import nl.fontys.s3.comfyshop.persistence.OrderRepository;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.OrderDetailsEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOrderUCImpl implements CreateOrderUC {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;

    @Override
    public void createOrder(Long userId) {
        UserEntity user = userRepository.getById(userId);
        orderRepository.save(OrderDetailsEntity.builder().user(user).build());
    }
}
