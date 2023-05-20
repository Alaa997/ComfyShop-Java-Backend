package nl.fontys.s3.comfyshop.controller;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.shoppingCart.CreateShoppingSessionUC;
import nl.fontys.s3.comfyshop.bussiness.exception.EmailAlreadyExistsException;
import nl.fontys.s3.comfyshop.bussiness.user.CreateUserUC;
import nl.fontys.s3.comfyshop.bussiness.user.LoginUC;
import nl.fontys.s3.comfyshop.dto.user.LoginRequest;
import nl.fontys.s3.comfyshop.dto.user.LoginResponse;
import nl.fontys.s3.comfyshop.dto.user.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final CreateUserUC createUserUC;
    private final CreateShoppingSessionUC cartUC;
    private final LoginUC loginUC;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO request) {
        try {
            UserDTO response = createUserUC.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = loginUC.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }

}
