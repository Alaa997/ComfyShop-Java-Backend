package nl.fontys.s3.comfyshop.persistence;

import nl.fontys.s3.comfyshop.persistence.entity.RoleEntity;
import nl.fontys.s3.comfyshop.persistence.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRole(RoleEnum role);
}
