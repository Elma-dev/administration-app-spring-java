package dev.elma.services.interfaces;

import java.util.Optional;


public interface ProfileServiceInt {
    Optional<?> showAllProfiles();
    Optional<?> showOneProfileUsingName(String profileName);
    Optional<?> showProfileUsingId(Long id);





}
