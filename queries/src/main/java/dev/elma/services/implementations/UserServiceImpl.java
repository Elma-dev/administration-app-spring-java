package dev.elma.services.implementations;


import dev.elma.repositories.UserRepository;
import dev.elma.dtos.UserDtoRequest;
import dev.elma.entities.ProfileEntity;
import dev.elma.entities.UserEntity;
import dev.elma.mappers.UserMapper;
import dev.elma.services.interfaces.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInt {

    @Override
    public Optional<?> showUserUsingUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<?> showUserUsingEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<?> showUsersHasProfileName(String profileName) {
        return Optional.empty();
    }
}
