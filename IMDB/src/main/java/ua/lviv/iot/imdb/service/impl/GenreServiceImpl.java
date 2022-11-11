package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.domain.Genre;
import ua.lviv.iot.imdb.exception.GenreNotFoundException;
import ua.lviv.iot.imdb.repository.GenreRepository;
import ua.lviv.iot.imdb.service.GenreService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;

    public Genre findById(Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Transactional
    public Genre create(Genre genre) {
        genreRepository.save(genre);
        return genre;
    }

    @Transactional
    public void update(Integer id, Genre uGenre) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
        //update
        genre.setType(uGenre.getType());
        genreRepository.save(genre);
    }

    @Transactional
    public void delete(Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
        genreRepository.delete(genre);
    }
}

