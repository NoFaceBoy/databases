package ua.lviv.iot.imdb.service;

import ua.lviv.iot.imdb.domain.Award;
import ua.lviv.iot.imdb.domain.Movie;

import java.util.List;

public interface AwardService extends GeneralService<Award, Integer> {
    public List<Movie> findMoviesByAwardId(Integer id);
    void createAwardMovieRelationship(String awardName, String movieName);
}
