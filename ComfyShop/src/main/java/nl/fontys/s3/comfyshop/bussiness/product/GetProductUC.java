package nl.fontys.s3.comfyshop.bussiness.product;

import nl.fontys.s3.comfyshop.dto.ProductDTO;

import java.util.Optional;

public interface GetProductUC {
    Optional<ProductDTO> getProduct(long id);
}
