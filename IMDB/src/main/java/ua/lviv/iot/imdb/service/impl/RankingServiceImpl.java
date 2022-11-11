package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.dao.RankingDao;
import ua.lviv.iot.imdb.domain.Ranking;
import ua.lviv.iot.imdb.service.RankingService;

import java.util.List;
import java.util.Optional;

@Service
public class RankingServiceImpl implements RankingService {
    @Autowired
    private RankingDao rankingDao;

    @Override
    public List<Ranking> findAll() {
        return rankingDao.findAll();
    }

    @Override
    public Optional<Ranking> findById(Integer id) {
        return rankingDao.findById(id);
    }

    @Override
    public int create(Ranking ranking) {
        return rankingDao.create(ranking);
    }

    @Override
    public int update(Integer id, Ranking ranking) {
        return rankingDao.update(id, ranking);
    }

    @Override
    public int delete(Integer id) {
        return rankingDao.delete(id);
    }
}
