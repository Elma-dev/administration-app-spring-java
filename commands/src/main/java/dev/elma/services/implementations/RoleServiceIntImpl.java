package dev.elma.services.implementations;


import dev.elma.repositories.RoleRepository;
import dev.elma.services.interfaces.RoleServiceInt;
import dev.elma.dtos.RoleDto;
import dev.elma.entities.RoleEntity;
import dev.elma.enums.RoleName;
import dev.elma.mappers.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceIntImpl implements RoleServiceInt {
    @Autowired
    private RoleRepository roleRepository;
    private final RoleMapper roleMapper=new RoleMapper();

    @Override
    public Optional<?> saveRole(RoleDto roleDto) {
        RoleEntity role = roleMapper.toRoleEntity(roleDto);
        try {

            return Optional.of(roleRepository.save(role));
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }

    @Override
    public Optional<?> updateRole(RoleDto roleDto) {
        RoleEntity role = roleMapper.toRoleEntity(roleDto);
        try {

            return Optional.of(roleRepository.save(role));
        }
        catch (Exception e) {
            return Optional.of(e);
        }

    }

    @Override
    public Optional<?> deleteRole(Long id) {
        try {
            roleRepository.findById(id).orElseThrow();
            roleRepository.deleteById(id);
            return Optional.of("Role Deleted");
        }
        catch (Exception e) {
            return Optional.of(e);
        }

    }

    @Override
    public Optional<?> findRoleByName(RoleName roleName) {
        try {
            RoleEntity role = roleRepository.findByRoleName(roleName).orElseThrow();
            return Optional.of(role);
        }
        catch (Exception e) {
            return Optional.of(e);
        }
    }
}
