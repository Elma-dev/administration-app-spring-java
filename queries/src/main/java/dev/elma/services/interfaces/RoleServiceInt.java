package dev.elma.services.interfaces;



import dev.elma.enums.RoleName;

import java.util.Optional;

public interface RoleServiceInt {
    Optional<?> showAllRoles();
    Optional<?> showOneRoleUsingId(Long id);
    Optional<?> findRoleByName(RoleName roleName);

}
