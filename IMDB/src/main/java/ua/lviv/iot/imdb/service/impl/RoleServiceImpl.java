package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.domain.Role;
import ua.lviv.iot.imdb.exception.RoleNotFoundException;
import ua.lviv.iot.imdb.repository.RoleRepository;
import ua.lviv.iot.imdb.repository.MovieRepository;
import ua.lviv.iot.imdb.service.RoleService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    MovieRepository movieRepository;

    public Role findById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional
    public Role create(Role role) {
        roleRepository.save(role);
        return role;
    }

    @Transactional
    public void update(Integer id, Role uRole) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
        //update
        role.setName(uRole.getName());
        roleRepository.save(role);
    }

    @Transactional
    public void delete(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
        roleRepository.delete(role);
    }
}

