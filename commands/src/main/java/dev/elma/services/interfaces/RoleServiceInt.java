package dev.elma.services.interfaces;



import dev.elma.dtos.RoleDto;
import dev.elma.enums.RoleName;

import java.util.Optional;

public interface RoleServiceInt {
    Optional<?> saveRole(RoleDto roleDto);
    Optional<?> updateRole(RoleDto roleDto);
    Optional<?> deleteRole(Long id);
    Optional<?> findRoleByName(RoleName roleName);

}
