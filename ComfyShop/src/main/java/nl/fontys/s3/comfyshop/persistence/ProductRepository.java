package nl.fontys.s3.comfyshop.persistence;

import nl.fontys.s3.comfyshop.dto.ProductStatistics;
import nl.fontys.s3.comfyshop.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByName(String name);
    List<ProductEntity> findAllByCategoryId(Long id);
    int countByCategoryId(Long categoryId);
    @Query("SELECT new nl.fontys.s3.comfyshop.dto.ProductStatistics(p.name, COUNT(p)) " +
            "FROM ProductEntity p " +
            "JOIN p.category c " +
            "JOIN p.cartItems ci " +
            "JOIN ci.shoppingSession ss " +
            "WHERE c.id = :categoryId " +
            "AND ss.ordered = true " +
            "GROUP BY p.name")
    List<ProductStatistics> findProductNamesAndCountByCategoryAndOrderedStatus(@Param("categoryId") Long categoryId);

    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
