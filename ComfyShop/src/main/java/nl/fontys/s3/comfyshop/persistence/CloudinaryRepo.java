package nl.fontys.s3.comfyshop.persistence;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryRepo {
    String uploadPicture(MultipartFile photo);
}
