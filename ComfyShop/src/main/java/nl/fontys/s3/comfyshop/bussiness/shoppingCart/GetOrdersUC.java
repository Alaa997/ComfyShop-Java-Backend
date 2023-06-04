package nl.fontys.s3.comfyshop.bussiness.shoppingCart;

import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;

import java.util.List;

public interface GetOrdersUC {
    List<ShoppingSessionDTO> getOrders(Long sessionId);
}
