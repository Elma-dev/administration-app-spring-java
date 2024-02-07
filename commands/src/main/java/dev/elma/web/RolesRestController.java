package dev.elma.web;

import dev.elma.repositories.RoleRepository;
import dev.elma.services.implementations.RoleServiceIntImpl;
import dev.elma.dtos.RoleDto;
import dev.elma.enums.RoleName;
import dev.elma.mappers.RoleMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RolesRestController {
    private final RoleServiceIntImpl roleServiceImpl;

    public RolesRestController(RoleServiceIntImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
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
