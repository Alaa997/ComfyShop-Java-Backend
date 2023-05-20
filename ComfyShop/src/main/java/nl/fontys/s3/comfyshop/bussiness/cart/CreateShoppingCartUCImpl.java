package nl.fontys.s3.comfyshop.bussiness.cart;

import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;

public interface CreateShoppingCartUCImpl {
    ShoppingSessionDTO createShoppingCart(ShoppingSessionDTO request);
}
