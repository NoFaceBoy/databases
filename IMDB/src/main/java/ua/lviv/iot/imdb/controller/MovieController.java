package ua.lviv.iot.imdb.controller;

import ua.lviv.iot.imdb.domain.Movie;

import java.util.Optional;

public interface MovieController extends GeneralController<Movie, Integer> {
    Optional<Movie> findByMovieName(String name);

    Optional<Movie> findByYear(Integer year);
}
