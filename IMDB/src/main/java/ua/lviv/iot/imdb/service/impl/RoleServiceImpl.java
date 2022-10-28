package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.dao.RoleDao;
import ua.lviv.iot.imdb.domain.Role;
import ua.lviv.iot.imdb.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleDao.findById(id);
    }

    @Override
    public int create(Role role) {
        return roleDao.create(role);
    }

    @Override
    public int update(Integer id, Role role) {
        return roleDao.update(id, role);
    }

    @Override
    public int delete(Integer id) {
        return roleDao.delete(id);
    }
}
