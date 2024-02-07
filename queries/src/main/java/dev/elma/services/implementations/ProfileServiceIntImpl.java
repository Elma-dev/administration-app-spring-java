package dev.elma.services.implementations;



import dev.elma.services.interfaces.ProfileServiceInt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceIntImpl implements ProfileServiceInt {
    @Override
    public Optional<?> showAllProfiles() {
        return Optional.empty();
    }

    @Override
    public Optional<?> showOneProfileUsingName(String profileName) {
        return Optional.empty();
    }

    @Override
    public Optional<?> showProfileUsingId(Long id) {
        return Optional.empty();
    }


}
