package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.domain.Award;
import ua.lviv.iot.imdb.domain.Movie;
import ua.lviv.iot.imdb.exception.AwardNotFoundException;
import ua.lviv.iot.imdb.exception.MovieNotFoundException;
import ua.lviv.iot.imdb.repository.AwardRepository;
import ua.lviv.iot.imdb.service.AwardService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {
    @Autowired
    AwardRepository awardRepository;

    public Award findById(Integer id) {
        return awardRepository.findById(id)
                .orElseThrow(() -> new AwardNotFoundException(id));
    }

    public List<Award> findAll() {
        return awardRepository.findAll();
    }

    @Transactional
    public Award create(Award award) {
        awardRepository.save(award);
        return award;
    }

    @Transactional
    public void update(Integer id, Award uAward) {
        Award award = awardRepository.findById(id)
                .orElseThrow(() -> new AwardNotFoundException(id));
        //update
        award.setName(uAward.getName());
        awardRepository.save(award);
    }

    @Transactional
    public void delete(Integer id) {
        Award award = awardRepository.findById(id)
                .orElseThrow(() -> new AwardNotFoundException(id));
        awardRepository.delete(award);
    }
    @Transactional
    public List<Movie> findMoviesByAwardId(Integer id) {
        Award award = awardRepository.findById(id)
                .orElseThrow(() -> new AwardNotFoundException(id));
        return award.getMovies().stream().toList();
    }

    @Transactional
    public void createAwardMovieRelationship(String awardName, String movieName) {
        awardRepository.createAwardMovieRelationship(awardName, movieName);
    }
}