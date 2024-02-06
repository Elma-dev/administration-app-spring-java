package dev.elma.commands.services.implementations;

import dev.elma.commands.dtos.UserDtoRequest;
import dev.elma.commands.entities.ProfileEntity;
import dev.elma.commands.entities.UserEntity;
import dev.elma.commands.mappers.UserMapper;
import dev.elma.commands.repositories.UserRepository;
import dev.elma.commands.services.interfaces.UserServiceInt;
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

    @Override
    public Optional<?> findUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<?> findUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<?> findUserByProfileName(String profileName) {
        return Optional.empty();
    }
}
