package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.domain.Nomination;
import ua.lviv.iot.imdb.exception.NominationNotFoundException;
import ua.lviv.iot.imdb.repository.NominationRepository;
import ua.lviv.iot.imdb.repository.AwardRepository;
import ua.lviv.iot.imdb.service.NominationService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NominationServiceImpl implements NominationService {
    @Autowired
    NominationRepository nominationRepository;
    @Autowired
    AwardRepository awardRepository;

    public Nomination findById(Integer id) {
        return nominationRepository.findById(id)
                .orElseThrow(() -> new NominationNotFoundException(id));
    }

    public List<Nomination> findAll() {
        return nominationRepository.findAll();
    }

    @Transactional
    public Nomination create(Nomination nomination) {
        nominationRepository.save(nomination);
        return nomination;
    }

    @Transactional
    public void update(Integer id, Nomination uNomination) {
        Nomination nomination = nominationRepository.findById(id)
                .orElseThrow(() -> new NominationNotFoundException(id));
        //update
        nomination.setType(uNomination.getType());
        nominationRepository.save(nomination);
    }

    @Transactional
    public void delete(Integer id) {
        Nomination nomination = nominationRepository.findById(id)
                .orElseThrow(() -> new NominationNotFoundException(id));
        nominationRepository.delete(nomination);
    }
}

