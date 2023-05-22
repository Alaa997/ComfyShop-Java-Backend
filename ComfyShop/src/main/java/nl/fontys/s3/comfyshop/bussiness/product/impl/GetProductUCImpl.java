package nl.fontys.s3.comfyshop.bussiness.product.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.dto.ProductDTO;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidProductException;
import nl.fontys.s3.comfyshop.bussiness.product.GetProductUC;
import nl.fontys.s3.comfyshop.mappers.ProductMapper;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class GetProductUCImpl implements GetProductUC {
    private final ProductRepository productRepository;
    @Override
    public Optional<ProductDTO> getProduct(long productId) {
        Optional<ProductEntity> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new InvalidProductException("PRODUCT_ID_INVALID");
        }
        return Optional.ofNullable(ProductMapper.mapperToDTO(productOptional.get()));
    }
}
