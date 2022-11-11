package ua.lviv.iot.imdb.dao.impl;

import ua.lviv.iot.imdb.domain.Movie;
import ua.lviv.iot.imdb.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class MovieDaoImpl implements MovieDao {
    private static final String FIND_ALL = "SELECT * FROM movie";
    private static final String CREATE = "INSERT movie(name, genre_id, ranking_id, directors_id, description, budget, year, facts) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE movie SET name=?, genre_id=?, ranking_id=?, directors_id=?, description=?, budget=?, year=?, facts=? WHERE id=?";
    private static final String DELETE = "DELETE FROM movie WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM movie WHERE id=?";
    private static final String FIND_BY_YEAR = "SELECT * FROM movie WHERE year=?";
    private static final String FIND_BY_MOVIE_NAME = "SELECT * FROM movie WHERE name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Movie.class));
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        Optional<Movie> movie;
        try {
            movie = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Movie.class), id));
        } catch (EmptyResultDataAccessException e) {
            movie = Optional.empty();
        }
        return movie;
    }

    @Override
    public int create(Movie movie) {
        return jdbcTemplate.update(CREATE, movie.getName(), movie.getGenreId(), movie.getRankingId(), movie.getDirectorsId(), movie.getDescription(), movie.getBudget(), movie.getYear(), movie.getFacts());
    }

    @Override
    public int update(Integer id, Movie movie) {
        return jdbcTemplate.update(UPDATE, movie.getName(), movie.getGenreId(), movie.getRankingId(), movie.getDirectorsId(), movie.getDescription(), movie.getBudget(), movie.getYear(), movie.getFacts(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Movie> findByMovieName(String name) {
        Optional<Movie> movie;
        try {
            movie = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_MOVIE_NAME,
                    BeanPropertyRowMapper.newInstance(Movie.class), name));
        } catch (EmptyResultDataAccessException e) {
            movie = Optional.empty();
        }
        return movie;
    }

    @Override
    public Optional<Movie> findByYear(Integer year) {
        Optional<Movie> movie;
        try {
            movie = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_YEAR,
                    BeanPropertyRowMapper.newInstance(Movie.class), year));
        } catch (EmptyResultDataAccessException e) {
            movie = Optional.empty();
        }
        return movie;
    }
}
