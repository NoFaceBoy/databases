package ua.lviv.iot.imdb.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.controller.GenreController;
import ua.lviv.iot.imdb.domain.Genre;
import ua.lviv.iot.imdb.service.GenreService;

import java.util.List;
import java.util.Optional;

@Service
public class GenreControllerImpl implements GenreController {
    @Autowired
    GenreService genreService;

    @Override
    public List<Genre> findAll() {
        return genreService.findAll();
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        return genreService.findById(id);
    }

    @Override
    public int create(Genre genre) {
        return genreService.create(genre);
    }

    @Override
    public int update(Integer id, Genre genre) {
        return genreService.update(id, genre);
    }

    @Override
    public int delete(Integer id) {
        return genreService.delete(id);
    }
}
