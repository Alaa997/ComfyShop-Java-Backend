package nl.fontys.s3.comfyshop.bussiness.cartItem;

import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;

import java.util.List;

public interface GetCartItemsUC {
    List<CartItemDTO> getCartItems(Long id);
}
