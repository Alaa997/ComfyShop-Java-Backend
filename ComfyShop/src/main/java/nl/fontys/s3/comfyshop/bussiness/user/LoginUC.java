package nl.fontys.s3.comfyshop.bussiness.user;

import nl.fontys.s3.comfyshop.dto.user.LoginRequest;
import nl.fontys.s3.comfyshop.dto.user.LoginResponse;

public interface LoginUC {
    LoginResponse login(LoginRequest loginRequest);
}
