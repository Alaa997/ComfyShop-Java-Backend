package nl.fontys.s3.comfyshop.bussiness.product;

import nl.fontys.s3.comfyshop.dto.ProductStatistics;

import java.util.List;

public interface GetStatisticsUC {
    List<ProductStatistics> getStatistics(Long categoryId);
}
