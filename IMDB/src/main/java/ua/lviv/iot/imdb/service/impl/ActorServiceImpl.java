package ua.lviv.iot.imdb.service.impl;

import  ua.lviv.iot.imdb.domain.Actor;
import  ua.lviv.iot.imdb.exception.ActorNotFoundException;
import  ua.lviv.iot.imdb.repository.ActorRepository;
import  ua.lviv.iot.imdb.repository.RoleRepository;
import  ua.lviv.iot.imdb.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    RoleRepository roleRepository;

    public Actor findById(Integer id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Transactional
    public Actor create(Actor actor) {
        actorRepository.save(actor);
        return actor;
    }

    @Transactional
    public void update(Integer id, Actor uActor) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
        //update
        actor.setFirstName(uActor.getFirstName());
        actor.setLastName(uActor.getLastName());
        actor.setGender(uActor.getGender());
        actor.setBirthdate(uActor.getBirthdate());
        actorRepository.save(actor);
    }

    @Transactional
    public void delete(Integer id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
        actorRepository.delete(actor);
    }
}

