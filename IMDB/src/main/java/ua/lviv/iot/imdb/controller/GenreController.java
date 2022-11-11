package ua.lviv.iot.imdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.imdb.domain.Genre;
import ua.lviv.iot.imdb.dto.GenreDto;
import ua.lviv.iot.imdb.dto.assembler.GenreDtoAssembler;
import ua.lviv.iot.imdb.service.GenreService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;
    @Autowired
    private GenreDtoAssembler genreDtoAssembler;

    @GetMapping(value = "/{genreId}")
    public ResponseEntity<GenreDto> getGenre(@PathVariable Integer genreId) {
        Genre genre = genreService.findById(genreId);
        GenreDto genreDto = genreDtoAssembler.toModel(genre);
        return new ResponseEntity<>(genreDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<GenreDto>> getAllGenres() {
        List<Genre> genres = genreService.findAll();
        CollectionModel<GenreDto> genreDtos = genreDtoAssembler.toCollectionModel(genres);
        return new ResponseEntity<>(genreDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<GenreDto> addGenre(@RequestBody Genre genre) {
        Genre newGenre = genreService.create(genre);
        GenreDto genreDto = genreDtoAssembler.toModel(newGenre);
        return new ResponseEntity<>(genreDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{genreId}")
    public ResponseEntity<?> updateGenre(@RequestBody Genre uGenre, @PathVariable Integer genreId) {
        genreService.update(genreId, uGenre);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{genreId}")
    public ResponseEntity<?> deleteGenre(@PathVariable Integer genreId) {
        genreService.delete(genreId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
