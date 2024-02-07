package dev.elma.services.implementations;


import dev.elma.repositories.UserRepository;
import dev.elma.services.interfaces.UserServiceInt;
import dev.elma.dtos.UserDtoRequest;
import dev.elma.entities.ProfileEntity;
import dev.elma.entities.UserEntity;
import dev.elma.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInt {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileServiceIntImpl profileServiceImpl;
    private final UserMapper userMapper = new UserMapper();

    @Override
    public Optional<?> saveUser(UserDtoRequest userDtoRequest) {
        try {
            String profileName = userDtoRequest.getProfileName();
            ProfileEntity profile = (ProfileEntity) profileServiceImpl.findProfileByName(profileName).orElseThrow();
            UserEntity userEntity = userMapper.toUserEntity(userDtoRequest);
            userEntity.setProfile(profile);
            return Optional.of(userRepository.save(userEntity));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> updateUser(Long id, UserDtoRequest userDtoRequest) {
        try {
            userRepository.findById(id).orElseThrow();
            UserEntity userEntity = userMapper.toUserEntity(userDtoRequest);
            userEntity.setId(id);
            userEntity.setProfile((ProfileEntity) profileServiceImpl.findProfileByName(userDtoRequest.getProfileName()).orElseThrow());
            return Optional.of(userRepository.save(userEntity));
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            userRepository.findById(id).orElseThrow();
            userRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new RuntimeException("User Not Found!");
        }
    }

}
