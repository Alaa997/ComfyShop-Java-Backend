package nl.fontys.s3.comfyshop.bussiness.user.impl;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.exception.UserNotFoundException;
import nl.fontys.s3.comfyshop.bussiness.user.GetCurrentUserUC;
import nl.fontys.s3.comfyshop.dto.user.UserDTO;
import nl.fontys.s3.comfyshop.mappers.UserMapper;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetCurrentUserUCImpl implements GetCurrentUserUC {
    private final UserRepository userRepository;
    @Override
    public UserDTO getCurrentUser(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        UserEntity userEntity = optionalUser.orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));

        return UserMapper.mapperToDTO(userEntity);
    }

}
