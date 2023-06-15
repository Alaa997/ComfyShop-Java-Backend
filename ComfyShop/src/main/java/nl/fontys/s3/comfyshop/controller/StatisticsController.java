package nl.fontys.s3.comfyshop.controller;

import lombok.RequiredArgsConstructor;
import nl.fontys.s3.comfyshop.bussiness.product.GetStatisticsUC;
import nl.fontys.s3.comfyshop.dto.ProductStatistics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
//@IsAuthenticated
//@RolesAllowed({"ROLE_ADMIN"})
public class StatisticsController {
    private final GetStatisticsUC getStatisticsUC;
    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ProductStatistics>> getStatistics(@PathVariable Long categoryId) {
        List<ProductStatistics> statistics = getStatisticsUC.getStatistics(categoryId);
        if (statistics.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(statistics);
    }
}
