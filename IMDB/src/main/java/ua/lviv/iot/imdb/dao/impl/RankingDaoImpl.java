package ua.lviv.iot.imdb.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.dao.RankingDao;
import ua.lviv.iot.imdb.domain.Ranking;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class RankingDaoImpl implements RankingDao {
    private static final String FIND_ALL = "SELECT * FROM ranking";
    private static final String CREATE = "INSERT ranking(rating, number_of_user_rates, metascore, number_of_critic_rates) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE ranking SET rating=?, number_of_user_rates=?, metascore=?, number_of_critic_rates=? WHERE id=?";
    private static final String DELETE = "DELETE FROM ranking WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM ranking WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Ranking> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Ranking.class));
    }

    @Override
    public Optional<Ranking> findById(Integer id) {
        Optional<Ranking> ranking;
        try {
            ranking = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Ranking.class), id));
        } catch (EmptyResultDataAccessException e) {
            ranking = Optional.empty();
        }
        return ranking;
    }

    @Override
    public int create(Ranking ranking) {
        return jdbcTemplate.update(CREATE, ranking.getRating(), ranking.getNumberOfUserRates(), ranking.getMetascore(), ranking.getNumberOfCriticRates());
    }

    @Override
    public int update(Integer id, Ranking ranking) {
        return jdbcTemplate.update(UPDATE, ranking.getRating(), ranking.getNumberOfUserRates(), ranking.getMetascore(), ranking.getNumberOfCriticRates(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
