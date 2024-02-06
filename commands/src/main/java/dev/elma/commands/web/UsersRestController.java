package dev.elma.commands.web;


import dev.elma.commands.dtos.UserDto;
import dev.elma.commands.dtos.UserDtoRequest;
import dev.elma.commands.entities.ProfileEntity;
import dev.elma.commands.entities.UserEntity;
import dev.elma.commands.mappers.UserMapper;
import dev.elma.commands.repositories.ProfileRepository;
import dev.elma.commands.repositories.UserRepository;
import dev.elma.commands.services.implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")

public class UsersRestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;
    private final UserMapper userMapper= new UserMapper();

    //Queries
    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        List<UserEntity> all = userRepository.findAll();
        return all.stream().map(userMapper::toUserDto).toList();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(userMapper.toUserDto(userRepository.findById(id).orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("User Not Found!", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            return  new ResponseEntity<>(userMapper.toUserDto(userRepository.findByUsername(username).orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("User Not Found!", HttpStatus.EXPECTATION_FAILED);
        }

    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(userMapper.toUserDto(userRepository.findByEmail(email).orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("User Not Found!", HttpStatus.EXPECTATION_FAILED);
        }
    }

    //Commands
    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDtoRequest userDtoRequest) {
        try {
            Optional<?> savedUser = userServiceImpl.saveUser(userDtoRequest);
            return new ResponseEntity<UserDto>(userMapper.toUserDto((UserEntity) savedUser.orElseThrow()), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Username or Email already exist or Profile Not Found!", HttpStatus.EXPECTATION_FAILED);
        }

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody UserDtoRequest userDtoRequest) {
        try {

            Optional<?> updatedUser = userServiceImpl.updateUser(id, userDtoRequest);
            return new ResponseEntity<>(userMapper.toUserDto((UserEntity) updatedUser.orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("User Not Found!", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userServiceImpl.deleteUser(id);
            return new ResponseEntity<>("User Deleted By Success!", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Failed Delete User!", HttpStatus.EXPECTATION_FAILED);
        }
    }




}
