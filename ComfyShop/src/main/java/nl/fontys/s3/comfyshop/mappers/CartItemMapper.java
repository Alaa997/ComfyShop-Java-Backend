package nl.fontys.s3.comfyshop.mappers;

import nl.fontys.s3.comfyshop.dto.shopping.CartItemDTO;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
import org.modelmapper.ModelMapper;

public class CartItemMapper {
    public CartItemMapper() {}

    public static CartItemDTO mapperToDTO(CartItemEntity cartItemEntity) {
        ModelMapper modelMapper = new ModelMapper();
        CartItemDTO cartItemDTO = modelMapper.map(cartItemEntity, CartItemDTO.class);
        return cartItemDTO;
    }
    public static CartItemEntity  mapperToEntity(CartItemDTO cartItemDTO) {
        ModelMapper modelMapper = new ModelMapper();
        CartItemEntity cartItemEntity = modelMapper.map(cartItemDTO, CartItemEntity.class);
        return cartItemEntity;
    }
}
