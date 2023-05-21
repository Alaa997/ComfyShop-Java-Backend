package nl.fontys.s3.comfyshop.persistence;

import nl.fontys.s3.comfyshop.persistence.entity.shopping.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
    //        @Query("SELECT c FROM CartItemEntity c WHERE c.shoppingSession.id = :sessionId")
//        List<CartItemEntity> findByShoppingSessionId(@Param("sessionId") Long sessionId);
    List<CartItemEntity> findByShoppingSessionId(Long sessionId);
    Optional<CartItemEntity> findByShoppingSessionIdAndProductId(Long sessionId, Long productId);

}
