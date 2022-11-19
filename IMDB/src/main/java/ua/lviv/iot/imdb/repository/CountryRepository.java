package ua.lviv.iot.imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.imdb.domain.Country;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Procedure("country_param_insert")
    Integer parametrizedInsertIntoTable(String name);

    @Procedure("insert_numbered_records")
    void insertNumberedRecords();
}


