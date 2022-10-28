package ua.lviv.iot.imdb.dao.impl;

import ua.lviv.iot.imdb.dao.ActorDao;
import ua.lviv.iot.imdb.domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class ActorDaoImpl implements ActorDao {
    private static final String FIND_ALL = "SELECT * FROM actor";
    private static final String CREATE = "INSERT actor(roles_id, first_name, last_name, gender, birthdate) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE actor SET roles_id=?, first_name=?, last_name=?, gender=?, birthdate=? WHERE id=?";
    private static final String DELETE = "DELETE FROM actor WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM actor WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Actor> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Actor.class));
    }

    @Override
    public Optional<Actor> findById(Integer id) {
        Optional<Actor> actor;
        try {
            actor = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Actor.class), id));
        } catch (EmptyResultDataAccessException e) {
            actor = Optional.empty();
        }
        return actor;
    }

    @Override
    public int create(Actor actor) {
        return jdbcTemplate.update(CREATE, actor.getRolesId(), actor.getFirstName(), actor.getLastName(), actor.getGender(), actor.getBirthdate());
    }

    @Override
    public int update(Integer id, Actor actor) {
        return jdbcTemplate.update(UPDATE, actor.getRolesId(), actor.getFirstName(), actor.getLastName(), actor.getGender(), actor.getBirthdate(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
