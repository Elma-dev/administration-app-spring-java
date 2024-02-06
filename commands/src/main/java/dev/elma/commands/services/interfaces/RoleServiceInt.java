package dev.elma.commands.services.interfaces;

import dev.elma.commands.dtos.RoleDto;
import dev.elma.commands.enums.RoleName;

import java.util.Optional;

public interface RoleServiceInt {
    Optional<?> saveRole(RoleDto roleDto);
    Optional<?> updateRole(RoleDto roleDto);
    Optional<?> deleteRole(Long id);
    Optional<?> findRoleByName(RoleName roleName);

}
