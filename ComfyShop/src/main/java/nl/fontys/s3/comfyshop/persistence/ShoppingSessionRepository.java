package nl.fontys.s3.comfyshop.persistence;

import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingSessionRepository extends JpaRepository<ShoppingSessionEntity, Long> {
    Optional<ShoppingSessionEntity> findByUserAndOrderedFalse(UserEntity user);

    //    List<ShoppingSessionEntity> findByUserAndOrderedTrue(UserEntity user);
//    List<ShoppingSessionEntity> getByUserShoppingSessionsAndCartItemsAndOrderedTrue(UserEntity user);
//    @Query("SELECT ss FROM ShoppingSessionEntity ss WHERE ss.user = :user AND ss.ordered = true")
//    List<ShoppingSessionEntity> getByUserAndOrderedTrue(UserEntity user);

    @Query("SELECT ss FROM ShoppingSessionEntity ss WHERE ss.user = :user AND ss.ordered = true ORDER BY ss.id DESC")
    List<ShoppingSessionEntity> getByUserAndOrderedTrueOrderedByIdDesc(UserEntity user);



}
