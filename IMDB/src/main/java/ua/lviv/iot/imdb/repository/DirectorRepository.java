package ua.lviv.iot.imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.imdb.domain.Director;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
    List<Director> findDirectorByLastName(String lastName);
}

