package ua.lviv.iot.imdb.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.imdb.controller.DirectorController;
import ua.lviv.iot.imdb.domain.Director;
import ua.lviv.iot.imdb.dto.DirectorDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DirectorDtoAssembler implements RepresentationModelAssembler<Director, DirectorDto> {
    @Override
    public DirectorDto toModel(Director entity) {
        DirectorDto directorDto = DirectorDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .birthdate(entity.getBirthdate())
                .build();
        Link selfLink = linkTo(methodOn(DirectorController.class).getDirector(directorDto.getId())).withSelfRel();
        directorDto.add(selfLink);
        return directorDto;
    }

    @Override
    public CollectionModel<DirectorDto> toCollectionModel(Iterable<? extends Director> entities) {
        CollectionModel<DirectorDto> directorDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DirectorController.class).getAllDirectors()).withSelfRel();
        directorDtos.add(selfLink);
        return directorDtos;
    }

    public CollectionModel<DirectorDto> toCollectionModel(Iterable<? extends Director> entities, Link link) {
        CollectionModel<DirectorDto> directorDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        directorDtos.add(link);
        return directorDtos;
    }
}
