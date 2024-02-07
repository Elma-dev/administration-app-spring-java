package dev.elma.services.interfaces;



import java.util.Optional;

public interface UserServiceInt {
    Optional<?> showUserUsingUsername(String username);
    Optional<?> showUserUsingEmail(String email);
    Optional<?> showUsersHasProfileName(String profileName);

}
