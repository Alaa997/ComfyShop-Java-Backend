package nl.fontys.s3.comfyshop.persistence;

import nl.fontys.s3.comfyshop.persistence.entity.UserEntity;
import nl.fontys.s3.comfyshop.persistence.entity.shopping.ShoppingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingSessionRepository extends JpaRepository<ShoppingSessionEntity, Long> {
    Optional<ShoppingSessionEntity> findByUserAndOrderedFalse(UserEntity user);
}
