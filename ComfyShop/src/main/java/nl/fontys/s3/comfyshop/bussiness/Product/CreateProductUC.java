package nl.fontys.s3.comfyshop.bussiness.Product;

import nl.fontys.s3.comfyshop.domain.Product.CreateProductRequest;
import nl.fontys.s3.comfyshop.domain.Product.CreateProductResponse;


public interface CreateProductUC {
    CreateProductResponse createProduct(CreateProductRequest request);
}
