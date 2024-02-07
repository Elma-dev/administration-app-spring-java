package dev.elma.services.interfaces;



import dev.elma.dtos.UserDtoRequest;

import java.util.Optional;

public interface UserServiceInt {
    Optional<?> saveUser(UserDtoRequest userDtoRequest);
    Optional<?> updateUser(Long id, UserDtoRequest userDtoRequest);
    void deleteUser(Long id);

}
