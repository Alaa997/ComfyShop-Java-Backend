package nl.fontys.s3.comfyshop.bussiness.shoppingCart;

import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;

public interface GetShoppingSessionUC {
    ShoppingSessionDTO getShoppingSession(UserEntity user);
}
