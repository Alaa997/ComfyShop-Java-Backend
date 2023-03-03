package nl.fontys.s3.comfyshop.bussiness.Category;

import nl.fontys.s3.comfyshop.domain.Category.CreateCategoryRequest;
import nl.fontys.s3.comfyshop.domain.Category.CreateCategoryResponse;
import nl.fontys.s3.comfyshop.domain.Product.CreateProductRequest;
import nl.fontys.s3.comfyshop.domain.Product.CreateProductResponse;

public interface CreateCategoryUC {
    CreateCategoryResponse createCategory(CreateCategoryRequest request);
}
