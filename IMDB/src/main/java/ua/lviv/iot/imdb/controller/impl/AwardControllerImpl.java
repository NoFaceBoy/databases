package ua.lviv.iot.imdb.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.controller.AwardController;
import ua.lviv.iot.imdb.domain.Award;
import ua.lviv.iot.imdb.service.AwardService;

import java.util.List;
import java.util.Optional;

@Service
public class AwardControllerImpl implements AwardController {
    @Autowired
    AwardService awardService;

    @Override
    public List<Award> findAll() {
        return awardService.findAll();
    }

    @Override
    public Optional<Award> findById(Integer id) {
        return awardService.findById(id);
    }

    @Override
    public int create(Award award) {
        return awardService.create(award);
    }

    @Override
    public int update(Integer id, Award award) {
        return awardService.update(id, award);
    }

    @Override
    public int delete(Integer id) {
        return awardService.delete(id);
    }
}
