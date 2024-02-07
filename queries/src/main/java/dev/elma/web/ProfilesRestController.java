package dev.elma.web;

import dev.elma.repositories.ProfileRepository;
import dev.elma.services.implementations.ProfileServiceIntImpl;
import dev.elma.dtos.ProfileDto;
import dev.elma.dtos.ProfileDtoRequest;
import dev.elma.entities.ProfileEntity;
import dev.elma.mappers.ProfileMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfilesRestController {
    private final ProfileRepository profileRepository;
    private final ProfileServiceIntImpl profileServiceImp;
    private final ProfileMapper profileMapper ;

    public ProfilesRestController(ProfileRepository profileRepository, ProfileServiceIntImpl profileServiceImp) {
        this.profileRepository = profileRepository;
        this.profileServiceImp = profileServiceImp;
        profileMapper = new ProfileMapper();
    }

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
            Optional<?> profile = profileServiceImp.findProfileByName(name);
            return new ResponseEntity<>(profileMapper.toProfileDto((ProfileEntity) profile.orElseThrow()), HttpStatus.OK) ;
        }
        catch (Exception e) {
            return new ResponseEntity<>("Profile Name Not Found!", HttpStatus.EXPECTATION_FAILED) ;
        }


    }
    //Queries
    @PostMapping("/add")
    public ResponseEntity<?> addProfile(@RequestBody ProfileDtoRequest profileDtoRequest) {
        try{
            Optional<?> profile = profileServiceImp.saveProfile(profileDtoRequest);
            return new ResponseEntity<>(profileMapper.toProfileDto((ProfileEntity) profile.orElseThrow()), HttpStatus.OK);

        }
        catch (Exception e) {
            return new ResponseEntity<>("Profile Not Added!", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody ProfileDtoRequest profileDtoRequest) {
        try {
            Optional<?> profile = profileServiceImp.updateProfile(id, profileDtoRequest);
            return new ResponseEntity<>(profileMapper.toProfileDto((ProfileEntity) profile.orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
            return new ResponseEntity<>("Profile Not Updated!", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        try {
            Optional<?> profile = profileServiceImp.deleteProfile(id);
            return new ResponseEntity<>(profileMapper.toProfileDto((ProfileEntity) profile.orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Profile Not Deleted!", HttpStatus.EXPECTATION_FAILED);
        }
    }
}
