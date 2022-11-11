package ua.lviv.iot.imdb.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.imdb.controller.MovieController;
import ua.lviv.iot.imdb.domain.Movie;
import ua.lviv.iot.imdb.dto.MovieDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MovieDtoAssembler implements RepresentationModelAssembler<Movie, MovieDto> {
    @Override
    public MovieDto toModel(Movie entity) {
        MovieDto movieDto = MovieDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .budget(entity.getBudget())
                .year(entity.getYear())
                .facts(entity.getFacts())
                .build();
        Link selfLink = linkTo(methodOn(MovieController.class).getMovie(movieDto.getId())).withSelfRel();
        movieDto.add(selfLink);
        return movieDto;
    }

    @Override
    public CollectionModel<MovieDto> toCollectionModel(Iterable<? extends Movie> entities) {
        CollectionModel<MovieDto> movieDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(MovieController.class).getAllMovies()).withSelfRel();
        movieDtos.add(selfLink);
        return movieDtos;
    }

    public CollectionModel<MovieDto> toCollectionModel(Iterable<? extends Movie> entities, Link link) {
        CollectionModel<MovieDto> movieDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        movieDtos.add(link);
        return movieDtos;
    }
}
