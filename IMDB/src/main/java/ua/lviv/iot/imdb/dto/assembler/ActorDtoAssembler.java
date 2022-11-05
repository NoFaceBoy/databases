package ua.lviv.iot.imdb.dto.assembler;

import ua.lviv.iot.imdb.controller.ActorController;
import ua.lviv.iot.imdb.domain.Actor;
import ua.lviv.iot.imdb.dto.ActorDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ActorDtoAssembler implements RepresentationModelAssembler<Actor, ActorDto> {
    @Override
    public ActorDto toModel(Actor entity) {
        ActorDto actorDto = ActorDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .gender(entity.getGender())
                .birthdate(entity.getBirthdate())
                .build();
        Link selfLink = linkTo(methodOn(ActorController.class).getActor(actorDto.getId())).withSelfRel();
        actorDto.add(selfLink);
        return actorDto;
    }

    @Override
    public CollectionModel<ActorDto> toCollectionModel(Iterable<? extends Actor> entities) {
        CollectionModel<ActorDto> actorDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ActorController.class).getAllActors()).withSelfRel();
        actorDtos.add(selfLink);
        return actorDtos;
    }

    public CollectionModel<ActorDto> toCollectionModel(Iterable<? extends Actor> entities, Link link) {
        CollectionModel<ActorDto> actorDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        actorDtos.add(link);
        return actorDtos;
    }
}
