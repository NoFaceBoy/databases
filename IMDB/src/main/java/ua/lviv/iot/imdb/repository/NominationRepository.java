package ua.lviv.iot.imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.imdb.domain.Nomination;

@Repository
public interface NominationRepository extends JpaRepository<Nomination, Integer> {
}

