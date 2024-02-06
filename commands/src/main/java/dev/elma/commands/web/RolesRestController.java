package dev.elma.commands.web;

import dev.elma.commands.dtos.RoleDto;
import dev.elma.commands.enums.RoleName;
import dev.elma.commands.mappers.RoleMapper;
import dev.elma.commands.repositories.RoleRepository;
import dev.elma.commands.services.implementations.RoleServiceIntImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolesRestController {
    private final RoleServiceIntImpl roleServiceImpl;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RolesRestController(RoleServiceIntImpl roleServiceImpl, RoleRepository roleRepository) {
        this.roleServiceImpl = roleServiceImpl;
        this.roleRepository = roleRepository;
        roleMapper = new RoleMapper();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRoles() {
        try {
            List<RoleDto> all = roleRepository.findAll().stream().map(roleMapper::toRoleDto).toList();
            return ResponseEntity.ok(all);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getRoleByName( @PathVariable String name) {
        try {
            Optional<?> role = roleServiceImpl.findRoleByName(RoleName.valueOf(name));
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(roleMapper.toRoleDto(roleRepository.findById(id).orElseThrow()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Role Not Found!", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody RoleDto roleDto) {
        try {
            return new ResponseEntity<>(roleServiceImpl.saveRole(roleDto), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Failed Add Role!", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        try {
            return new ResponseEntity<>(roleServiceImpl.updateRole(roleDto), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Role Not Updated!", HttpStatus.EXPECTATION_FAILED);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(roleServiceImpl.deleteRole(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
        }
    }



}
