package dev.elma.services.implementations;


import dev.elma.entities.RoleEntity;
import dev.elma.enums.RoleName;
import dev.elma.repositories.RoleRepository;
import dev.elma.services.interfaces.RoleServiceInt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceIntImpl implements RoleServiceInt {
    private final RoleRepository roleRepository;


    public RoleServiceIntImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<?> showAllRoles() {
        try {

            return Optional.of(roleRepository.findAll());
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> showOneRoleUsingId(Long id) {
        try {
            RoleEntity role = (RoleEntity) roleRepository.findById(id).orElseThrow();
            return Optional.of(role);
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> findRoleByName(RoleName roleName) {
        try {
            RoleEntity role =(RoleEntity) roleRepository.findByRoleName(roleName).orElseThrow();
            return Optional.of(role);
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }
}
