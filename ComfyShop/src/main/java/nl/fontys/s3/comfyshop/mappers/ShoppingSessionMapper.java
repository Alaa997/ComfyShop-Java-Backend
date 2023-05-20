package nl.fontys.s3.comfyshop.mappers;

import nl.fontys.s3.comfyshop.dto.shopping.ShoppingSessionDTO;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.modelmapper.ModelMapper;

public class ShoppingSessionMapper {
    public ShoppingSessionMapper(){}
    public static ShoppingSessionDTO toDTO(ShoppingSessionEntity shoppingSessionEntity){
        ModelMapper modelMapper = new ModelMapper();
        ShoppingSessionDTO sessionDTO = modelMapper.map(shoppingSessionEntity, ShoppingSessionDTO.class);
        return sessionDTO;
    }
    public static ShoppingSessionEntity toEntity(ShoppingSessionDTO shoppingSessionDTO){
        ModelMapper modelMapper = new ModelMapper();
        ShoppingSessionEntity sessionEntity = modelMapper.map(shoppingSessionDTO, ShoppingSessionEntity.class);
        return sessionEntity;
    }
}
