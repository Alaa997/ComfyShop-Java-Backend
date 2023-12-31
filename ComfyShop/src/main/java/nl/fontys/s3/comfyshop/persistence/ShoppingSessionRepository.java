package nl.fontys.s3.comfyshop.persistence;

import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingSessionRepository extends JpaRepository<ShoppingSessionEntity, Long> {
    Optional<ShoppingSessionEntity> findByUserAndOrderedFalse(UserEntity user);
    @Query("SELECT ss.id FROM ShoppingSessionEntity ss WHERE ss.user.id = :userId AND ss.ordered = false")
    Long findShoppingSessionIdByUserIdAndNotOrdered(@Param("userId") Long userId);
    @Query("SELECT ss FROM ShoppingSessionEntity ss WHERE ss.user = :user AND ss.ordered = true ORDER BY ss.id DESC")
    List<ShoppingSessionEntity> getByUserAndOrderedTrueOrderedByIdDesc(UserEntity user);
    @Query("SELECT SUM(c.quantity * p.price) as total FROM ShoppingSessionEntity ss " +
            "JOIN ss.cartItems c " +
            "JOIN c.product p " +
            "WHERE ss.id = :shoppingSessionId " +
            "GROUP BY ss.id")
    BigDecimal getTotalPriceByShoppingSessionId(@Param("shoppingSessionId") Long shoppingSessionId);

}
