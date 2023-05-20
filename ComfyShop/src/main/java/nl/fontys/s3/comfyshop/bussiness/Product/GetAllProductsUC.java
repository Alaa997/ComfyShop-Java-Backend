package nl.fontys.s3.comfyshop.bussiness.Product;

import nl.fontys.s3.comfyshop.dto.ProductDTO;

import java.util.List;

public interface GetAllProductsUC {
    List<ProductDTO> getAllProducts();
}
