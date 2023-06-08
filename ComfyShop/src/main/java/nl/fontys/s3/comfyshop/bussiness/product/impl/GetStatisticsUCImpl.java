package nl.fontys.s3.comfyshop.bussiness.product.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.product.GetStatisticsUC;
import nl.fontys.s3.comfyshop.dto.ProductStatistics;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class GetStatisticsUCImpl implements GetStatisticsUC {
    private final ProductRepository productRepository;
    @Override
    public List<ProductStatistics> getStatistics(Long categoryId) {
        return productRepository.findProductNamesAndCountByCategoryAndOrderedStatus(categoryId);
    }
}
