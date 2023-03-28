package nl.fontys.s3.comfyshop.bussiness.product;

import nl.fontys.s3.comfyshop.DTO.ProductDTO;

import java.util.List;

public interface GetProductsUC {
    List<ProductDTO> getProducts(long categoryId);
}
