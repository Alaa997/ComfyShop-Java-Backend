package nl.fontys.s3.comfyshop.bussiness.product.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.product.GetAllProductsUC;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllProductsUCImpl implements GetAllProductsUC {
    private final ProductRepository productRepository;
    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity>  products = productRepository.findAll();
        return ProductMapper.mapperToDTOList(products);
    }
}
