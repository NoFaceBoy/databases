package ua.lviv.iot.imdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.imdb.domain.Award;
import ua.lviv.iot.imdb.domain.Movie;
import ua.lviv.iot.imdb.dto.AwardDto;
import ua.lviv.iot.imdb.dto.MovieDto;
import ua.lviv.iot.imdb.dto.assembler.MovieDtoAssembler;
import ua.lviv.iot.imdb.service.MovieService;
import ua.lviv.iot.imdb.dto.assembler.AwardDtoAssembler;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private AwardDtoAssembler awardDtoAssembler;
    @Autowired
    private MovieDtoAssembler movieDtoAssembler;

    @GetMapping(value = "/{movieId}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Integer movieId) {
        Movie movie = movieService.findById(movieId);
        MovieDto movieDto = movieDtoAssembler.toModel(movie);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<MovieDto>> getAllMovies() {
        List<Movie> movies = movieService.findAll();
        CollectionModel<MovieDto> movieDtos = movieDtoAssembler.toCollectionModel(movies);
        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<MovieDto> addMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.create(movie);
        MovieDto movieDto = movieDtoAssembler.toModel(newMovie);
        return new ResponseEntity<>(movieDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{movieId}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie uMovie, @PathVariable Integer movieId) {
        movieService.update(movieId, uMovie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer movieId) {
        movieService.delete(movieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{movieId}/awards")
    public ResponseEntity<CollectionModel<AwardDto>> getAllAwardsForMovie(@PathVariable Integer movieId) {
        List<Award> awards = movieService.findAwardsByMovieId(movieId);
        Link selfLink = linkTo(methodOn(MovieController.class).getAllAwardsForMovie(movieId)).withSelfRel();
        CollectionModel<AwardDto> awardDtos = awardDtoAssembler.toCollectionModel(awards, selfLink);
        return new ResponseEntity<>(awardDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/companies/{companyId}")
    public ResponseEntity<CollectionModel<MovieDto>> getMoviesByCompanyId(@PathVariable Integer companyId) {
        List<Movie> movies = movieService.findMoviesByCompanyId(companyId);
        Link selfLink = linkTo(methodOn(MovieController.class).getMoviesByCompanyId(companyId)).withSelfRel();
        CollectionModel<MovieDto> movieDtos = movieDtoAssembler.toCollectionModel(movies, selfLink);
        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/average_budget")
    public ResponseEntity<BigDecimal> getAverageBudget() {
        BigDecimal averageBudget = movieService.getAverageBudget();
        return new ResponseEntity<>(averageBudget, HttpStatus.OK);
    }

    @PostMapping("/create-tables")
    public ResponseEntity<?> createTablesUsingCursor() {
        movieService.createTablesUsingCursor();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}