package ua.lviv.iot.imdb.service;

import ua.lviv.iot.imdb.domain.Movie;

import java.util.Optional;

public interface MovieService extends GeneralService<Movie, Integer> {
    Optional<Movie> findByMovieName(String bookName);

    Optional<Movie> findByYear(Integer year);
}
