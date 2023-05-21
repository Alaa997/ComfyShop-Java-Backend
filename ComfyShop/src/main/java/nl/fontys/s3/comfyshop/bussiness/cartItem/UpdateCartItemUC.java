package nl.fontys.s3.comfyshop.bussiness.cartItem;

import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;

public interface UpdateCartItemUC {
    boolean updateCartItem(CartItemDTO request);
}
