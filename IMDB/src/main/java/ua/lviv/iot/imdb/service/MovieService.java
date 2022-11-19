package ua.lviv.iot.imdb.service;

import ua.lviv.iot.imdb.domain.Award;
import ua.lviv.iot.imdb.domain.Movie;

import java.math.BigDecimal;
import java.util.List;

public interface MovieService extends GeneralService<Movie, Integer> {
    public List<Award> findAwardsByMovieId(Integer id);
    public List<Movie> findMoviesByCompanyId(Integer id);
    BigDecimal getAverageBudget();
}