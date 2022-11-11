package ua.lviv.iot.imdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.imdb.domain.Role;
import ua.lviv.iot.imdb.dto.RoleDto;
import ua.lviv.iot.imdb.dto.assembler.RoleDtoAssembler;
import ua.lviv.iot.imdb.service.RoleService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleDtoAssembler roleDtoAssembler;

    @GetMapping(value = "/{roleId}")
    public ResponseEntity<RoleDto> getRole(@PathVariable Integer roleId) {
        Role role = roleService.findById(roleId);
        RoleDto roleDto = roleDtoAssembler.toModel(role);
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RoleDto>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        CollectionModel<RoleDto> roleDtos = roleDtoAssembler.toCollectionModel(roles);
        return new ResponseEntity<>(roleDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<RoleDto> addRole(@RequestBody Role role) {
        Role newRole = roleService.create(role);
        RoleDto roleDto = roleDtoAssembler.toModel(newRole);
        return new ResponseEntity<>(roleDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{roleId}")
    public ResponseEntity<?> updateRole(@RequestBody Role uRole, @PathVariable Integer roleId) {
        roleService.update(roleId, uRole);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Integer roleId) {
        roleService.delete(roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
