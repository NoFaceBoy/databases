package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.domain.Award;
import ua.lviv.iot.imdb.domain.Company;
import ua.lviv.iot.imdb.domain.Movie;
import ua.lviv.iot.imdb.exception.CompanyNotFoundException;
import ua.lviv.iot.imdb.exception.MovieNotFoundException;
import ua.lviv.iot.imdb.repository.MovieRepository;
import ua.lviv.iot.imdb.repository.GenreRepository;
import ua.lviv.iot.imdb.repository.CompanyRepository;
import ua.lviv.iot.imdb.repository.DirectorRepository;
import ua.lviv.iot.imdb.service.MovieService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    DirectorRepository directorRepository;

    public Movie findById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Transactional
    public Movie create(Movie movie) {
        movieRepository.save(movie);
        return movie;
    }

    @Transactional
    public void update(Integer id, Movie uMovie) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        //update
        movie.setName(uMovie.getName());
        movie.setDescription(uMovie.getDescription());
        movie.setBudget(uMovie.getBudget());
        movie.setYear(uMovie.getYear());
        movie.setFacts(uMovie.getFacts());
        movieRepository.save(movie);
    }

    @Transactional
    public void delete(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        movieRepository.delete(movie);
    }

    public List<Award> findAwardsByMovieId(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        return movie.getAwards().stream().toList();
    }

    @Override
    public List<Movie> findMoviesByCompanyId(Integer companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException(companyId));
        return company.getMovies();
    }

    @Override
    public BigDecimal getAverageBudget() {
        return movieRepository.getAverageBudget();
    }

    @Override
    public void createTablesUsingCursor() {
        movieRepository.createTablesUsingCursor();
    }
}