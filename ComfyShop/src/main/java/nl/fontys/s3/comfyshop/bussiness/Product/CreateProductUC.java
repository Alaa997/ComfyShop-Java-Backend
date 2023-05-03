package nl.fontys.s3.comfyshop.bussiness.product;

import nl.fontys.s3.comfyshop.DTO.ProductDTO;


public interface CreateProductUC {
    ProductDTO createProduct(ProductDTO request);
}
