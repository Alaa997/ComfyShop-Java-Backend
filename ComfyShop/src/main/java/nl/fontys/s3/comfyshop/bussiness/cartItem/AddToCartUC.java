package nl.fontys.s3.comfyshop.bussiness.cartItem;

import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;

public interface AddToCartUC {
    boolean addToCart(CartItemDTO request);
}
