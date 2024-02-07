package dev.elma.services.implementations;


import dev.elma.repositories.RoleRepository;
import dev.elma.dtos.RoleDto;
import dev.elma.entities.RoleEntity;
import dev.elma.enums.RoleName;
import dev.elma.mappers.RoleMapper;
import dev.elma.services.interfaces.RoleServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceIntImpl implements RoleServiceInt {

    @Override
    public Optional<?> showAllRoles() {
        return Optional.empty();
    }

    @Override
    public Optional<?> showOneRoleUsingId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<?> findRoleByName(RoleName roleName) {
        return Optional.empty();
    }
}
