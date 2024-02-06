package dev.elma.commands.repositories;

import dev.elma.commands.entities.RoleEntity;
import dev.elma.commands.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(RoleName roleName);
}
