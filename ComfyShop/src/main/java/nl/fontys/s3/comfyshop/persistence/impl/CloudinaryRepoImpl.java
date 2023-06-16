package nl.fontys.s3.comfyshop.persistence.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import nl.fontys.s3.comfyshop.persistence.CloudinaryRepo;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CloudinaryRepoImpl implements CloudinaryRepo {
    @Override
    public String uploadPicture(MultipartFile photo) {
        Map<String, String> config = new HashMap();
        config.put("cloud_name", "dxwerfhnx");
        config.put("api_key", "271514134437483");
        config.put("api_secret", "uKoXov8zaPNxcwe0fM5Kgsin7c4");
        Cloudinary cloudinary = new Cloudinary(config);

        try {
            Map uploadResult = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = uploadResult.get("url").toString();
            return imageUrl;

        } catch (IOException exception) {
            throw new RuntimeException("Picture uploading went wrong");
        }
    }
}
