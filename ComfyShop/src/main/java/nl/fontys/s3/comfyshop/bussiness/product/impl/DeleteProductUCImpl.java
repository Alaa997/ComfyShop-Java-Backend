package nl.fontys.s3.comfyshop.bussiness.product.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.exception.InvalidProductException;
import nl.fontys.s3.comfyshop.bussiness.product.DeleteProductUC;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteProductUCImpl implements DeleteProductUC {
    private final ProductRepository productRepository;

    @Override
    public void deleteProduct(long id) {
        if (!existsById(id)) {
            throw new InvalidProductException("PRODUCT_ID_INVALID");
        }
        this.productRepository.deleteById(id);
    }
    private boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}
