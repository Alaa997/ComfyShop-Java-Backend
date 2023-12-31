package nl.fontys.s3.comfyshop.bussiness.user.impl;

import nl.fontys.s3.comfyshop.bussiness.shoppingCart.CreateShoppingSessionUC;
import nl.fontys.s3.comfyshop.dto.user.UserDTO;
import nl.fontys.s3.comfyshop.mappers.UserMapper;
import nl.fontys.s3.comfyshop.persistence.RoleRepository;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.RoleEntity;
import nl.fontys.s3.comfyshop.persistence.entity.RoleEnum;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUCImplTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private CreateShoppingSessionUC createShoppingCartUCMock;

    @Mock
    private RoleRepository roleRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoderMock;

    @InjectMocks
    private CreateUserUCImpl createUserUC;

    @Test
    void createUser() {
        UserDTO request = UserDTO.builder()
                .id(1L)
                .email("john.doe@example.com")
                .password("password123")
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .build();

        RoleEntity roleEntity = RoleEntity.builder()
                .id(1L)
                .role(RoleEnum.CUSTOMER)
                .build();

        UserEntity savedUser = UserEntity.builder()
                .id(1L)
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password("encodedPassword")
                .address(request.getAddress())
                .role(roleEntity)
                .build();

        UserEntity expectedUser = UserEntity.builder()
                .id(1L)
                .email("john.doe@example.com")
                .password("encodedPassword")
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .role(roleEntity)
                .build();

        when(userRepositoryMock.existsByEmail(request.getEmail())).thenReturn(false);
        when(roleRepositoryMock.findByRole(RoleEnum.CUSTOMER)).thenReturn(roleEntity);
        when(passwordEncoderMock.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepositoryMock.save(expectedUser)).thenReturn(savedUser);

        // Act
        UserDTO result = createUserUC.createUser(request);

        // Assertions or verifications
        verify(userRepositoryMock).existsByEmail(request.getEmail());
        verify(roleRepositoryMock).findByRole(RoleEnum.CUSTOMER);
        verify(userRepositoryMock).save(expectedUser);
        verify(createShoppingCartUCMock).createShoppingSession(savedUser);

        // Additional assertions if needed
        assertEquals(UserMapper.mapperToDTO(expectedUser), result);
    }
}