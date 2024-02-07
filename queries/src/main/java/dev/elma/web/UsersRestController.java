package dev.elma.web;


import dev.elma.dtos.UserDto;
import dev.elma.entities.UserEntity;
import dev.elma.mappers.UserMapper;
import dev.elma.services.implementations.UserServiceImpl;
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
@RequestMapping("/api/users")

public class UsersRestController {

    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;

    public UsersRestController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        userMapper = new UserMapper();
    }

    //Queries
    @GetMapping("/all")
    public  ResponseEntity<?> getAllUsers() {
        try {
            Optional<?> users = userServiceImpl.showAllUsers();
            System.out.println(users);
            List<UserDto> listUsers = new ArrayList<>();
            users.ifPresent(
                    user -> {
                        ((List<UserEntity>) user).forEach(
                                userEntity -> listUsers.add(userMapper.toUserDto(userEntity))
                        );
                    }
            );
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("No Users Found!", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(userMapper.toUserDto((UserEntity) userServiceImpl.showUserUsingId(id).orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("User Not Found!", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            return  new ResponseEntity<>(userMapper.toUserDto((UserEntity) userServiceImpl.showUserUsingUsername(username).orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("User Not Found!", HttpStatus.EXPECTATION_FAILED);
        }

    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(userMapper.toUserDto((UserEntity) userServiceImpl.showUserUsingEmail(email).orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("User Not Found!", HttpStatus.EXPECTATION_FAILED);
        }
    }





}
