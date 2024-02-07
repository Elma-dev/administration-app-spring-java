package dev.elma.repositories;

import dev.elma.entities.RoleEntity;
import dev.elma.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(RoleName roleName);
}
