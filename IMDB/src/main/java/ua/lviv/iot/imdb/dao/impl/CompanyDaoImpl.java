package ua.lviv.iot.imdb.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.dao.CompanyDao;
import ua.lviv.iot.imdb.domain.Company;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class CompanyDaoImpl implements CompanyDao {
    private static final String FIND_ALL = "SELECT * FROM company";
    private static final String CREATE = "INSERT company(movie_id, name) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE company SET movie_id=?, name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM company WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM company WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Company> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Company.class));
    }

    @Override
    public Optional<Company> findById(Integer id) {
        Optional<Company> company;
        try {
            company = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Company.class), id));
        } catch (EmptyResultDataAccessException e) {
            company = Optional.empty();
        }
        return company;
    }

    @Override
    public int create(Company company) {
        return jdbcTemplate.update(CREATE, company.getMovieId(), company.getName());
    }

    @Override
    public int update(Integer id, Company company) {
        return jdbcTemplate.update(UPDATE, company.getMovieId(), company.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
