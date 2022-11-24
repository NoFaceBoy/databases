package ua.lviv.iot.imdb.controller;

import net.minidev.json.JSONObject;
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
import ua.lviv.iot.imdb.dto.assembler.AwardDtoAssembler;
import ua.lviv.iot.imdb.dto.assembler.MovieDtoAssembler;
import ua.lviv.iot.imdb.service.AwardService;


import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping(value = "/api/awards")
public class AwardController {
    @Autowired
    private AwardService awardService;
    @Autowired
    private AwardDtoAssembler awardDtoAssembler;
    @Autowired
    private MovieDtoAssembler movieDtoAssembler;

    @GetMapping(value = "/{awardId}")
    public ResponseEntity<AwardDto> getAward(@PathVariable Integer awardId) {
        Award award = awardService.findById(awardId);
        AwardDto awardDto = awardDtoAssembler.toModel(award);
        return new ResponseEntity<>(awardDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<AwardDto>> getAllAwards() {
        List<Award> awards = awardService.findAll();
        CollectionModel<AwardDto> awardDtos = awardDtoAssembler.toCollectionModel(awards);
        return new ResponseEntity<>(awardDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<AwardDto> addAward(@RequestBody Award award) {
        Award newAward = awardService.create(award);
        AwardDto awardDto = awardDtoAssembler.toModel(newAward);
        return new ResponseEntity<>(awardDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{awardId}")
    public ResponseEntity<?> updateAward(@RequestBody Award uAward, @PathVariable Integer awardId) {
        awardService.update(awardId, uAward);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{awardId}")
    public ResponseEntity<?> deleteAward(@PathVariable Integer awardId) {
        awardService.delete(awardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{awardId}/movies")
    public ResponseEntity<CollectionModel<MovieDto>> getAllMoviesForAward(@PathVariable Integer awardId) {
        List<Movie> movies = awardService.findMoviesByAwardId(awardId);
        Link selfLink = linkTo(methodOn(AwardController.class).getAllMoviesForAward(awardId)).withSelfRel();
        CollectionModel<MovieDto> movieDtos = movieDtoAssembler.toCollectionModel(movies, selfLink);
        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/create_relationship")
    public ResponseEntity<?> createAwardMovieRelationship(@RequestBody JSONObject jsonObject) {
        awardService.createAwardMovieRelationship(jsonObject.getAsString("award_name"), jsonObject.getAsString("movie_name"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}