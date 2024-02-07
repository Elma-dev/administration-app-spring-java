package dev.elma.commands.services.interfaces;

import dev.elma.common.dtos.ProfileDtoRequest;

import java.util.Optional;


public interface ProfileServiceInt {
    Optional<?> saveProfile(ProfileDtoRequest profileDtoRequest);
    Optional<?> updateProfile(Long id,ProfileDtoRequest profileDtoRequest);
    Optional<?> deleteProfile( Long id);
    Optional<?> findProfileByName(String profileName);




}
