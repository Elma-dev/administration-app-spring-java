package dev.elma.commands.repositories;

import dev.elma.commands.entities.ProfileEntity;
import dev.elma.commands.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findUserEntitiesByProfile(ProfileEntity profileEntity);

    UserEntity findByUsername(String username);

    UserEntity findByEmail(String email);
}
