package nl.fontys.s3.comfyshop.bussiness.user.impl;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidCredentialsException;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidUserException;
import nl.fontys.s3.comfyshop.bussiness.user.AccessTokenEncoder;
import nl.fontys.s3.comfyshop.bussiness.user.LoginUC;
import nl.fontys.s3.comfyshop.dto.user.AccessToken;
import nl.fontys.s3.comfyshop.dto.user.LoginRequest;
import nl.fontys.s3.comfyshop.dto.user.LoginResponse;
import nl.fontys.s3.comfyshop.persistence.UserRepository;
import nl.fontys.s3.comfyshop.persistence.entity.RoleEnum;
import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginUCImpl implements LoginUC {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        UserEntity user = optionalUser.orElseThrow(() -> new InvalidUserException("User does not exist"));

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }


    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    private String generateAccessToken(UserEntity user) {
        RoleEnum role = user.getRole().getRole();
        List<String> roles = Collections.singletonList(role.toString());

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getEmail())
                        .roles(roles)
                        .id(user.getId())
                        .build());
    }

}
