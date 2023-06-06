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
        List<Object[]> result = productRepository.findProductNamesAndCountByCategoryAndOrderedStatus(categoryId);
        List<ProductStatistics> statistics = new ArrayList<>();
        for (Object[] obj : result) {
            String productName = (String) obj[0];
            Long count = (Long) obj[1];
            ProductStatistics productStatistics = new ProductStatistics(productName, count);
            statistics.add(productStatistics);
        }
        return statistics;
    }
}
