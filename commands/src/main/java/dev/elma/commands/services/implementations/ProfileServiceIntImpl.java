package dev.elma.commands.services.implementations;

import dev.elma.commands.dtos.ProfileDtoRequest;
import dev.elma.commands.entities.ProfileEntity;
import dev.elma.commands.entities.RoleEntity;
import dev.elma.commands.mappers.ProfileMapper;
import dev.elma.commands.repositories.ProfileRepository;
import dev.elma.commands.services.interfaces.ProfileServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceIntImpl implements ProfileServiceInt {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private RoleServiceIntImpl roleServiceImp;
    private final ProfileMapper profileMapper = new ProfileMapper();

    @Override
    public Optional<?> saveProfile(ProfileDtoRequest profileDtoRequest) {
        try{
            List<RoleEntity> roles = profileDtoRequest.getRolesNames().stream().map(roleName ->
                    (RoleEntity)roleServiceImp.findRoleByName(roleName).orElseThrow()
            ).toList();

            System.out.println(roles);

            ProfileEntity profile = profileMapper.toProfileEntity(profileDtoRequest);
            profile.setRoles(roles);

            return Optional.of(profileRepository.save(profile));
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> updateProfile(Long id,ProfileDtoRequest profileDtoRequest) {
        try {
            profileRepository.findById(id).orElseThrow();
            List<RoleEntity> roles = profileDtoRequest.getRolesNames().stream().map(roleName ->
                    (RoleEntity)roleServiceImp.findRoleByName(roleName).orElseThrow()
            ).toList();

            ProfileEntity profile= profileMapper.toProfileEntity(profileDtoRequest);
            profile.setId(id);
            profile.setRoles(roles);

            profileRepository.save(profile);
            return Optional.of(profile);
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> deleteProfile(Long id) {
        try {
            profileRepository.findById(id).orElseThrow();
            profileRepository.deleteById(id);
            return Optional.of("Profile Deleted");
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> findProfileByName(String profileName) {
        try {
            ProfileEntity profile = profileRepository.findByProfileNameIgnoreCase(profileName).orElseThrow();
            return Optional.of(profile);
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }
}
