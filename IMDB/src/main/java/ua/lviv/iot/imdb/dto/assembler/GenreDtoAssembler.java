package ua.lviv.iot.imdb.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.imdb.controller.GenreController;
import ua.lviv.iot.imdb.domain.Genre;
import ua.lviv.iot.imdb.dto.GenreDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GenreDtoAssembler implements RepresentationModelAssembler<Genre, GenreDto> {
    @Override
    public GenreDto toModel(Genre entity) {
        GenreDto genreDto = GenreDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .build();
        Link selfLink = linkTo(methodOn(GenreController.class).getGenre(genreDto.getId())).withSelfRel();
        genreDto.add(selfLink);
        return genreDto;
    }

    @Override
    public CollectionModel<GenreDto> toCollectionModel(Iterable<? extends Genre> entities) {
        CollectionModel<GenreDto> genreDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(GenreController.class).getAllGenres()).withSelfRel();
        genreDtos.add(selfLink);
        return genreDtos;
    }

    public CollectionModel<GenreDto> toCollectionModel(Iterable<? extends Genre> entities, Link link) {
        CollectionModel<GenreDto> genreDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        genreDtos.add(link);
        return genreDtos;
    }
}
