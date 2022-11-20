package ua.lviv.iot.imdb.service;

import ua.lviv.iot.imdb.domain.Award;
import ua.lviv.iot.imdb.domain.Movie;

import java.math.BigDecimal;
import java.util.List;

public interface MovieService extends GeneralService<Movie, Integer> {
    List<Award> findAwardsByMovieId(Integer id);
    List<Movie> findMoviesByCompanyId(Integer id);
    BigDecimal getAverageBudget();
    void createTablesUsingCursor();
}