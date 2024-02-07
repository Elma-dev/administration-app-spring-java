package dev.elma.web;

import dev.elma.dtos.RoleDto;
import dev.elma.entities.RoleEntity;
import dev.elma.enums.RoleName;
import dev.elma.mappers.RoleMapper;
import dev.elma.services.implementations.RoleServiceIntImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolesRestController {
    private final RoleServiceIntImpl roleServiceImpl;
    private final RoleMapper roleMapper;

    public RolesRestController(RoleServiceIntImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
        roleMapper = new RoleMapper();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRoles() {
        try {
               Optional<?> rolesOp = roleServiceImpl.showAllRoles();
            List<RoleDto> listRoles =  new ArrayList<>();

            rolesOp.ifPresent(
                    roles -> {
                        ((List<RoleEntity>) roles).forEach(
                                userEntity -> listRoles.add(roleMapper.toRoleDto(userEntity))
                        );
                    }
            );
            return new ResponseEntity<>(listRoles, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("No Roles Found!", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getRoleByName( @PathVariable String name) {
        try {
            Optional<?> role = roleServiceImpl.findRoleByName(RoleName.valueOf(name));
            return new ResponseEntity<>(roleMapper.toRoleDto((RoleEntity) role.orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Role Not Found!", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        try {
            Optional<?> role = roleServiceImpl.showOneRoleUsingId(id);
            return new ResponseEntity<>(roleMapper.toRoleDto((RoleEntity) role.orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Role Id Not Found!", HttpStatus.EXPECTATION_FAILED);
        }
    }



}
