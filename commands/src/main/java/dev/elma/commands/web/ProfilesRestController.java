package dev.elma.commands.web;

import dev.elma.commands.dtos.ProfileDto;
import dev.elma.commands.entities.ProfileEntity;
import dev.elma.commands.entities.UserEntity;
import dev.elma.commands.mappers.ProfileMapper;
import dev.elma.commands.repositories.ProfileRepository;
import dev.elma.commands.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfilesRestController {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;
    private final ProfileMapper profileMapper = new ProfileMapper();

    @GetMapping("/all")
    public List<ProfileDto> getAllProfiles() {
        List<ProfileEntity> all = profileRepository.findAll();
        return all.stream().map(profileMapper::toProfileDto).toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {
        try {
            ProfileEntity profile = profileRepository.findById(id).orElseThrow();
            return new ResponseEntity<>(profileMapper.toProfileDto(profile), HttpStatus.OK) ;
        }
        catch (Exception e) {
            return new ResponseEntity<>("Profile Id Not Found!", HttpStatus.EXPECTATION_FAILED) ;
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProfileByName(@PathVariable String name) {
        try {
            ProfileEntity profile = profileRepository.findByProfileName(name).orElseThrow();
            return new ResponseEntity<>(profileMapper.toProfileDto(profile), HttpStatus.OK) ;
        }
        catch (Exception e) {
            return new ResponseEntity<>("Profile Name Not Found!", HttpStatus.EXPECTATION_FAILED) ;
        }


    }
}
