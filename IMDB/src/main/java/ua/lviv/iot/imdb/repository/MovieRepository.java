package ua.lviv.iot.imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.imdb.domain.Movie;

import java.math.BigDecimal;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "CALL count_average_budget();", nativeQuery = true)
    BigDecimal getAverageBudget();

    @Procedure("create_tables_using_cursor")
    void createTablesUsingCursor();
}

