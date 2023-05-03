package nl.fontys.s3.comfyshop.mappers;

import nl.fontys.s3.comfyshop.dto.user.UserDTO;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import org.modelmapper.ModelMapper;

public class UserMapper {
    public UserMapper() {
    }
    public static UserDTO mapperToDTO(UserEntity userEntity) {
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }
    public static UserEntity  mapperToEntity(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        return userEntity;
    }
}
