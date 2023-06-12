package nl.fontys.s3.comfyshop.bussiness.product.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.product.SearchProductsByNameUC;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class SearchProductsByNameUCImpl implements SearchProductsByNameUC {
    private final ProductRepository productRepository;
    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        List<ProductEntity> productEntities = productRepository.findByNameContainingIgnoreCase(name);

        if (productEntities.isEmpty()) {
            return new ArrayList<>();
        }

        List<ProductDTO> productDTOS = ProductMapper.mapperToDTOList(productEntities);

        return productDTOS;
    }
}
