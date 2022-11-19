package ua.lviv.iot.imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.imdb.domain.Award;

@Repository
public interface AwardRepository extends JpaRepository<Award, Integer> {
    @Procedure("create_award_movie_relationship")
    void createAwardMovieRelationship(String awardName, String movieName);
}

