package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.dao.DirectorDao;
import ua.lviv.iot.imdb.domain.Director;
import ua.lviv.iot.imdb.service.DirectorService;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    private DirectorDao directorDao;

    @Override
    public List<Director> findAll() {
        return directorDao.findAll();
    }

    @Override
    public Optional<Director> findById(Integer id) {
        return directorDao.findById(id);
    }

    @Override
    public int create(Director director) {
        return directorDao.create(director);
    }

    @Override
    public int update(Integer id, Director director) {
        return directorDao.update(id, director);
    }

    @Override
    public int delete(Integer id) {
        return directorDao.delete(id);
    }
}
