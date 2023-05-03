package nl.fontys.s3.comfyshop.bussiness.product;

import nl.fontys.s3.comfyshop.dto.ProductDTO;

import java.util.List;

public interface GetAllProductsUC {
    List<ProductDTO> getAllProducts();
}
