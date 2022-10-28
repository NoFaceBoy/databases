package ua.lviv.iot.imdb.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.dao.DirectorDao;
import ua.lviv.iot.imdb.domain.Director;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class DirectorDaoImpl implements DirectorDao {
    private static final String FIND_ALL = "SELECT * FROM director";
    private static final String CREATE = "INSERT director(first_name, last_name, gender, birthdate) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE director SET first_name=?, last_name=?, gender=?, birthdate=? WHERE id=?";
    private static final String DELETE = "DELETE FROM director WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM director WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Director> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Director.class));
    }

    @Override
    public Optional<Director> findById(Integer id) {
        Optional<Director> director;
        try {
            director = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Director.class), id));
        } catch (EmptyResultDataAccessException e) {
            director = Optional.empty();
        }
        return director;
    }

    @Override
    public int create(Director director) {
        return jdbcTemplate.update(CREATE, director.getFirstName(), director.getLastName(), director.getGender(), director.getBirthdate());
    }

    @Override
    public int update(Integer id, Director director) {
        return jdbcTemplate.update(UPDATE, director.getFirstName(), director.getLastName(), director.getGender(), director.getBirthdate(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
