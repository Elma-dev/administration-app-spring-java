package dev.elma.commands.repositories;

import dev.elma.common.entities.ProfileEntity;
import dev.elma.common.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findUserEntitiesByProfile(ProfileEntity profileEntity);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}
