package ua.lviv.iot.imdb.dao;

import ua.lviv.iot.imdb.domain.Movie;

import java.util.Optional;

public interface MovieDao extends GeneralDao<Movie, Integer> {
    Optional<Movie> findByYear(Integer year);

    Optional<Movie> findByMovieName(String name);
}
