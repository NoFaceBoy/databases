package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.dao.GenreDao;
import ua.lviv.iot.imdb.domain.Genre;
import ua.lviv.iot.imdb.service.GenreService;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreDao genreDao;

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        return genreDao.findById(id);
    }

    @Override
    public int create(Genre genre) {
        return genreDao.create(genre);
    }

    @Override
    public int update(Integer id, Genre genre) {
        return genreDao.update(id, genre);
    }

    @Override
    public int delete(Integer id) {
        return genreDao.delete(id);
    }
}
