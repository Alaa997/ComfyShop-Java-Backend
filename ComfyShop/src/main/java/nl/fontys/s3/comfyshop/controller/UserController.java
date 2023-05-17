package nl.fontys.s3.comfyshop.controller;

import lombok.AllArgsConstructor;
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
    private final LoginUC loginUC;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO request){
        UserDTO response = createUserUC.createUser(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest){
        LoginResponse loginResponse = loginUC.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }

}
