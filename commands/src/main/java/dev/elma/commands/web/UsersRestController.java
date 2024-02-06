package dev.elma.commands.web;


import dev.elma.commands.dtos.UserDto;
import dev.elma.commands.dtos.UserDtoRequest;
import dev.elma.commands.entities.ProfileEntity;
import dev.elma.commands.entities.UserEntity;
import dev.elma.commands.mappers.UserMapper;
import dev.elma.commands.repositories.ProfileRepository;
import dev.elma.commands.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UsersRestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    private final UserMapper userMapper= new UserMapper();

    //Queries
    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        List<UserEntity> all = userRepository.findAll();
        return all.stream().map(userMapper::toUserDto).toList();
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.toUserDto(userRepository.findById(id).orElse(null));
    }
    @GetMapping("/username/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userMapper.toUserDto(userRepository.findByUsername(username));
    }
    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userMapper.toUserDto(userRepository.findByEmail(email));
    }

    //Commands
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDtoRequest userDtoRequest) {
        try {
            String profileName = userDtoRequest.getProfileName();
            ProfileEntity profile = profileRepository.findByProfileName(profileName).orElseThrow();
            UserEntity userEntity = userMapper.toUserEntity(userDtoRequest);
            userEntity.setProfile(profile);
            return new ResponseEntity<UserDto>(userMapper.toUserDto(userRepository.save(userEntity)), HttpStatus.CREATED);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage().equals("No value present")?"Profile not exist!":"Username or Email already exist!", HttpStatus.EXPECTATION_FAILED);
        }

    }
    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable Long id,@RequestBody UserDto userDto) {
        UserEntity userEntity = userMapper.toUserEntity(userDto);
        userEntity.setId(id);
        return userMapper.toUserDto(userRepository.save(userEntity));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }




}
