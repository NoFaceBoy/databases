package ua.lviv.iot.imdb.service.impl;

import ua.lviv.iot.imdb.dao.ActorDao;
import ua.lviv.iot.imdb.domain.Actor;
import ua.lviv.iot.imdb.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorDao actorDao;

    @Override
    public List<Actor> findAll() {
        return actorDao.findAll();
    }

    @Override
    public Optional<Actor> findById(Integer id) {
        return actorDao.findById(id);
    }

    @Override
    public int create(Actor actor) {
        return actorDao.create(actor);
    }

    @Override
    public int update(Integer id, Actor actor) {
        return actorDao.update(id, actor);
    }

    @Override
    public int delete(Integer id) {
        return actorDao.delete(id);
    }
}
