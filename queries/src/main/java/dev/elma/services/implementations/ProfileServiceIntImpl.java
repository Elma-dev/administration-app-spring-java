package dev.elma.services.implementations;


import dev.elma.entities.ProfileEntity;
import dev.elma.repositories.ProfileRepository;
import dev.elma.services.interfaces.ProfileServiceInt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceIntImpl implements ProfileServiceInt {
    private final ProfileRepository profileRepository;

    public ProfileServiceIntImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<?> showAllProfiles() {
        try {
            return Optional.of(profileRepository.findAll());
        }
        catch (Exception e) {
            return Optional.of(e);
        }

    }

    @Override
    public Optional<?> showOneProfileUsingName(String profileName) {
        try {
            ProfileEntity profile = (ProfileEntity) profileRepository.findByProfileNameIgnoreCase(profileName).orElseThrow();
            return Optional.of(profile);
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> showProfileUsingId(Long id) {
        try {
            ProfileEntity profile =(ProfileEntity) profileRepository.findById(id).orElseThrow();
            return Optional.of(profile);
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }


}
