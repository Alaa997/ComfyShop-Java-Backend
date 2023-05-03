package nl.fontys.s3.comfyshop.bussiness.user;

import nl.fontys.s3.comfyshop.dto.user.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
