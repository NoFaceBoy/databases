package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.domain.Director;
import ua.lviv.iot.imdb.exception.DirectorNotFoundException;
import ua.lviv.iot.imdb.repository.DirectorRepository;
import ua.lviv.iot.imdb.service.DirectorService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    DirectorRepository directorRepository;

    public Director findById(Integer id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));
    }

    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Transactional
    public Director create(Director director) {
        directorRepository.save(director);
        return director;
    }

    @Transactional
    public void update(Integer id, Director uDirector) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));
        //update
        director.setFirstName(uDirector.getFirstName());
        director.setLastName(uDirector.getLastName());
        director.setGender(uDirector.getGender());
        director.setBirthdate(uDirector.getBirthdate());
        directorRepository.save(director);
    }

    @Transactional
    public void delete(Integer id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));
        directorRepository.delete(director);
    }
}

