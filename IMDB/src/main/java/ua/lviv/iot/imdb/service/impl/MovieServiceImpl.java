package ua.lviv.iot.imdb.service.impl;

import ua.lviv.iot.imdb.dao.MovieDao;
import ua.lviv.iot.imdb.domain.Movie;
import ua.lviv.iot.imdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        return movieDao.findById(id);
    }

    @Override
    public int create(Movie movie) {
        return movieDao.create(movie);
    }

    @Override
    public int update(Integer id, Movie movie) {
        return movieDao.update(id, movie);
    }

    @Override
    public int delete(Integer id) {
        return movieDao.delete(id);
    }

    @Override
    public Optional<Movie> findByMovieName(String name) {
        return movieDao.findByMovieName(name);
    }

    @Override
    public Optional<Movie> findByYear(Integer year) {
        return movieDao.findByYear(year);
    }
}
