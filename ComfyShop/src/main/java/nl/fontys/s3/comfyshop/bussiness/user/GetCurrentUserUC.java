package nl.fontys.s3.comfyshop.bussiness.user;

import nl.fontys.s3.comfyshop.dto.user.UserDTO;

public interface GetCurrentUserUC {
    UserDTO getCurrentUser(String email);
}
