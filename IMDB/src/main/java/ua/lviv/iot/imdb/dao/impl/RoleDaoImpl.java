package ua.lviv.iot.imdb.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.dao.RoleDao;
import ua.lviv.iot.imdb.domain.Role;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class RoleDaoImpl implements RoleDao {
    private static final String FIND_ALL = "SELECT * FROM role";
    private static final String CREATE = "INSERT role(movie_id, role) VALUES (?)";
    private static final String UPDATE = "UPDATE role SET movie_id=?, role=? WHERE id=?";
    private static final String DELETE = "DELETE FROM role WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM role WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Role.class));
    }

    @Override
    public Optional<Role> findById(Integer id) {
        Optional<Role> role;
        try {
            role = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Role.class), id));
        } catch (EmptyResultDataAccessException e) {
            role = Optional.empty();
        }
        return role;
    }

    @Override
    public int create(Role role) {
        return jdbcTemplate.update(CREATE, role.getMovieId(), role.getRole());
    }

    @Override
    public int update(Integer id, Role role) {
        return jdbcTemplate.update(UPDATE, role.getMovieId(), role.getRole(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
