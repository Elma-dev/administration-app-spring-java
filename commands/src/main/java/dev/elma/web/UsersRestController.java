package dev.elma.web;


import dev.elma.repositories.UserRepository;
import dev.elma.services.implementations.UserServiceImpl;
import dev.elma.dtos.UserDto;
import dev.elma.dtos.UserDtoRequest;
import dev.elma.entities.UserEntity;
import dev.elma.mappers.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
