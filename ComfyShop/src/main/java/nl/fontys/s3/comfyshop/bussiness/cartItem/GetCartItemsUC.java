package nl.fontys.s3.comfyshop.bussiness.cartItem;

import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;

import java.util.List;

public interface GetCartItemsUC {
    List<CartItemEntity> getCartItems(Long id);
}
