package ua.lviv.iot.imdb.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.controller.RoleController;
import ua.lviv.iot.imdb.domain.Role;
import ua.lviv.iot.imdb.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleControllerImpl implements RoleController {
    @Autowired
    RoleService roleService;

    @Override
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleService.findById(id);
    }

    @Override
    public int create(Role role) {
        return roleService.create(role);
    }

    @Override
    public int update(Integer id, Role role) {
        return roleService.update(id, role);
    }

    @Override
    public int delete(Integer id) {
        return roleService.delete(id);
    }
}
