package dev.elma.services.interfaces;



import java.util.Optional;

public interface UserServiceInt {
    Optional<?> showAllUsers();
    Optional<?> showUserUsingId(Long id);
    Optional<?> showUserUsingUsername(String username);
    Optional<?> showUserUsingEmail(String email);
    Optional<?> showUsersHasProfileName(String profileName);

}
