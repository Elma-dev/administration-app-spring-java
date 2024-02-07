package dev.elma.web;

import dev.elma.dtos.ProfileDto;
import dev.elma.entities.ProfileEntity;
import dev.elma.entities.UserEntity;
import dev.elma.mappers.ProfileMapper;
import dev.elma.services.implementations.ProfileServiceIntImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfilesRestController {

    private final ProfileServiceIntImpl profileServiceImp;
    private final ProfileMapper profileMapper ;

    public ProfilesRestController( ProfileServiceIntImpl profileServiceImp) {
        this.profileServiceImp = profileServiceImp;
        profileMapper = new ProfileMapper();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProfiles() {
       try {
           Optional<?> profilesOp = profileServiceImp.showAllProfiles();
           List<ProfileDto> profilesList = new ArrayList<>();
           profilesOp.ifPresent(
                   profiles -> {
                       ((List<ProfileEntity>) profiles).forEach(
                               profileEntity -> profilesList.add(profileMapper.toProfileDto(profileEntity))
                       );
                   }
           );
           return new ResponseEntity<>(profilesList, HttpStatus.OK) ;
         }
         catch (Exception e) {
              return new ResponseEntity<>("No Profiles Found!", HttpStatus.EXPECTATION_FAILED) ;
       }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {
        try {
            Optional<?> profile = profileServiceImp.showProfileUsingId(id);
            return new ResponseEntity<>(profileMapper.toProfileDto((ProfileEntity) profile.orElseThrow()), HttpStatus.OK) ;
        }
        catch (Exception e) {
            return new ResponseEntity<>("Profile Id Not Found!", HttpStatus.EXPECTATION_FAILED) ;
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getProfileByName(@PathVariable String name) {
        try {
                Optional<?> profile = profileServiceImp.showOneProfileUsingName(name);
            return new ResponseEntity<>(profileMapper.toProfileDto((ProfileEntity) profile.orElseThrow()), HttpStatus.OK) ;
        }
        catch (Exception e) {
            return new ResponseEntity<>("Profile Name Not Found!", HttpStatus.EXPECTATION_FAILED) ;
        }

    }
}
