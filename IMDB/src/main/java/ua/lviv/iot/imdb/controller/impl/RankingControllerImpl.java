package ua.lviv.iot.imdb.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.controller.RankingController;
import ua.lviv.iot.imdb.domain.Ranking;
import ua.lviv.iot.imdb.service.RankingService;

import java.util.List;
import java.util.Optional;

@Service
public class RankingControllerImpl implements RankingController {
    @Autowired
    RankingService rankingService;

    @Override
    public List<Ranking> findAll() {
        return rankingService.findAll();
    }

    @Override
    public Optional<Ranking> findById(Integer id) {
        return rankingService.findById(id);
    }

    @Override
    public int create(Ranking ranking) {
        return rankingService.create(ranking);
    }

    @Override
    public int update(Integer id, Ranking ranking) {
        return rankingService.update(id, ranking);
    }

    @Override
    public int delete(Integer id) {
        return rankingService.delete(id);
    }
}
