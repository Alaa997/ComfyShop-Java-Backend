package nl.fontys.s3.comfyshop.bussiness.product;

import nl.fontys.s3.comfyshop.dto.ProductDTO;

import java.util.List;

public interface SearchProductsByNameUC {
    List<ProductDTO> searchProductsByName(String name);
}
