package nl.fontys.s3.comfyshop.bussiness.product.impl;

import nl.fontys.s3.comfyshop.dto.ProductStatistics;
import nl.fontys.s3.comfyshop.persistence.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetStatisticsUCImplTest {
    @Mock
    private ProductRepository productRepositoryMock;
    @InjectMocks
    private GetStatisticsUCImpl getStatisticsUC;
    @Test
    void getStatistics() {
        // Arrange
        Long categoryId = 1L;
        List<ProductStatistics> mockStatistics = new ArrayList<>();
        mockStatistics.add(new ProductStatistics("Product A", 10L));
        mockStatistics.add(new ProductStatistics("Product B", 5L));
        mockStatistics.add(new ProductStatistics("Product C", 3L));

        when(productRepositoryMock.findProductNamesAndCountByCategoryAndOrderedStatus(categoryId)).thenReturn(mockStatistics);

        // Act
        List<ProductStatistics> result = getStatisticsUC.getStatistics(categoryId);

        // Assert
        verify(productRepositoryMock).findProductNamesAndCountByCategoryAndOrderedStatus(categoryId);
    }
}