package nl.fontys.s3.comfyshop.bussiness.product;

import nl.fontys.s3.comfyshop.dto.ProductDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface CreateProductUC {
    ProductDTO createProduct(MultipartHttpServletRequest request);
}
