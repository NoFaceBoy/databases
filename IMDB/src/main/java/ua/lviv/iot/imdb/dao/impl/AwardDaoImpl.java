package ua.lviv.iot.imdb.dao.impl;

import ua.lviv.iot.imdb.domain.Award;
import ua.lviv.iot.imdb.dao.AwardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class AwardDaoImpl implements AwardDao {
    private static final String FIND_ALL = "SELECT * FROM award";
    private static final String CREATE = "INSERT award(movie_id, name, year) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE award SET movie_id=?, name=?, year=? WHERE id=?";
    private static final String DELETE = "DELETE FROM award WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM award WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Award> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Award.class));
    }

    @Override
    public Optional<Award> findById(Integer id) {
        Optional<Award> award;
        try {
            award = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Award.class), id));
        } catch (EmptyResultDataAccessException e) {
            award = Optional.empty();
        }
        return award;
    }

    @Override
    public int create(Award award) {
        return jdbcTemplate.update(CREATE, award.getMovieId(), award.getName(), award.getYear());
    }

    @Override
    public int update(Integer id, Award award) {
        return jdbcTemplate.update(UPDATE, award.getMovieId(), award.getName(), award.getYear(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
