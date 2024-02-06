package dev.elma.commands.repositories;

import dev.elma.commands.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByProfileNameIgnoreCase(String name);
}
