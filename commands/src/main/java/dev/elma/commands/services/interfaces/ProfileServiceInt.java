package dev.elma.commands.services.interfaces;

import dev.elma.commands.dtos.ProfileDto;
import dev.elma.commands.dtos.ProfileDtoRequest;
import dev.elma.commands.entities.ProfileEntity;

import java.util.Optional;

public interface ProfileServiceInt {
    Optional<?> saveProfile(ProfileDtoRequest profileDtoRequest);
    Optional<?> updateProfile(Long id,ProfileDtoRequest profileDtoRequest);
    Optional<?> deleteProfile( Long id);
    Optional<?> findProfileByName(String profileName);




}
