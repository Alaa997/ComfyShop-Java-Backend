package nl.fontys.s3.comfyshop.bussiness.user.impl;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.CreateShoppingSessionUC;
import nl.fontys.s3.comfyshop.bussiness.exception.EmailAlreadyExistsException;
import nl.fontys.s3.comfyshop.bussiness.user.CreateUserUC;
import nl.fontys.s3.comfyshop.dto.user.UserDTO;
import nl.fontys.s3.comfyshop.mappers.UserMapper;
import nl.fontys.s3.comfyshop.persistence.RoleRepository;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.RoleEnum;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateUserUCImpl implements CreateUserUC {
    private final UserRepository userRepository;
    private final CreateShoppingSessionUC createShoppingCartUC;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDTO createUser(UserDTO request) {
        if (existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        UserEntity savedUser = saveNewUser(UserMapper.mapperToEntity(request), request.getPassword());
        createShoppingCartUC.createShoppingSession(savedUser);
        return UserMapper.mapperToDTO(savedUser);
    }

    private UserEntity saveNewUser(UserEntity user, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRole(roleRepository.findByRole(RoleEnum.CUSTOMER));
        return userRepository.save(user);
    }

    private boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
