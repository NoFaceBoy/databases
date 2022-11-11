package ua.lviv.iot.imdb.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.controller.DirectorController;
import ua.lviv.iot.imdb.domain.Director;
import ua.lviv.iot.imdb.service.DirectorService;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorControllerImpl implements DirectorController {
    @Autowired
    DirectorService directorService;

    @Override
    public List<Director> findAll() {
        return directorService.findAll();
    }

    @Override
    public Optional<Director> findById(Integer id) {
        return directorService.findById(id);
    }

    @Override
    public int create(Director director) {
        return directorService.create(director);
    }

    @Override
    public int update(Integer id, Director director) {
        return directorService.update(id, director);
    }

    @Override
    public int delete(Integer id) {
        return directorService.delete(id);
    }
}
