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
    private final ProfileServiceIntImpl profileServiceImp;
    private final ProfileMapper profileMapper ;

    public ProfilesRestController(ProfileServiceIntImpl profileServiceImp) {
        this.profileServiceImp = profileServiceImp;
        profileMapper = new ProfileMapper();
    }

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
