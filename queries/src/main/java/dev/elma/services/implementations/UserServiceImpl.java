package dev.elma.services.implementations;


import dev.elma.entities.ProfileEntity;
import dev.elma.entities.UserEntity;
import dev.elma.repositories.ProfileRepository;
import dev.elma.repositories.UserRepository;
import dev.elma.services.interfaces.UserServiceInt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInt {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public UserServiceImpl(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<?> showAllUsers() {
        try {
            return Optional.of(userRepository.findAll());
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> showUserUsingId(Long id) {
        try {
            UserEntity user = (UserEntity) userRepository.findById(id).orElseThrow();
            return Optional.of(user);
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> showUserUsingUsername(String username) {
        try {
            UserEntity user = (UserEntity) userRepository.findByUsername(username).orElseThrow();
            return Optional.of(user);
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> showUserUsingEmail(String email) {
        try {
            UserEntity user = (UserEntity) userRepository.findByEmail(email).orElseThrow();
            return Optional.of(user);
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> showUsersHasProfileName(String profileName) {
        try {
            ProfileEntity profile = profileRepository.findByProfileNameIgnoreCase(profileName).orElseThrow();
            UserEntity user = (UserEntity) userRepository.findUserEntitiesByProfile(profile);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.of(e);
        }
    }
}
