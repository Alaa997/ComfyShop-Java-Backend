package nl.fontys.s3.comfyshop.bussiness.user.impl;

import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCredentialsException;
import nl.fontys.s3.comfyshop.bussiness.user.AccessTokenEncoder;
import nl.fontys.s3.comfyshop.dto.user.AccessToken;
import nl.fontys.s3.comfyshop.dto.user.LoginRequest;
import nl.fontys.s3.comfyshop.dto.user.LoginResponse;
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
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUCImplTest {
    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private PasswordEncoder passwordEncoderMock;

    @Mock
    private AccessTokenEncoder accessTokenEncoderMock;

    @InjectMocks
    private LoginUCImpl loginUC;

    @Test
    void login() {
        // Mock necessary dependencies
        LoginRequest request = LoginRequest.builder()
                .email("john.doe@example.com")
                .password("password123")
                .build();

        RoleEntity roleEntity = RoleEntity.builder()
                .id(1L)
                .role(RoleEnum.CUSTOMER)
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email(request.getEmail())
                .password("encodedPassword")
                .role(roleEntity)
                .build();

        when(userRepositoryMock.findByEmail(request.getEmail())).thenReturn(userEntity);
        when(passwordEncoderMock.matches(request.getPassword(), userEntity.getPassword())).thenReturn(true);
        when(accessTokenEncoderMock.encode(any(AccessToken.class))).thenReturn("encodedAccessToken");

        // Call the method to be tested
        LoginResponse result = loginUC.login(request);

        // Assertions or verifications
        verify(userRepositoryMock).findByEmail(request.getEmail());
        verify(passwordEncoderMock).matches(request.getPassword(), userEntity.getPassword());
        verify(accessTokenEncoderMock).encode(any(AccessToken.class));

        // Additional assertions if needed
        assertEquals("encodedAccessToken", result.getAccessToken());
    }

    @Test
    void loginWithIncorrectPassword() {
        // Mock necessary dependencies
        LoginRequest request = LoginRequest.builder()
                .email("john.doe@example.com")
                .password("invalidpassword")
                .build();
        UserEntity user = UserEntity.builder()
                .email("john.doe@example.com")
                .password(passwordEncoderMock.encode("password123"))
                .role(RoleEntity.builder().role(RoleEnum.CUSTOMER).build())
                .build();
        when(userRepositoryMock.findByEmail(request.getEmail())).thenReturn(user);

        // Call the method to be tested and assert that it throws an exception
        assertThrows(InvalidCredentialsException.class, () -> loginUC.login(request));

        // Assertions or verifications
        verify(userRepositoryMock).findByEmail(request.getEmail());
        verify(passwordEncoderMock).matches(request.getPassword(), user.getPassword());
    }
}