package ua.lviv.iot.imdb.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.imdb.controller.NominationController;
import ua.lviv.iot.imdb.domain.Nomination;
import ua.lviv.iot.imdb.dto.NominationDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NominationDtoAssembler implements RepresentationModelAssembler<Nomination, NominationDto> {
    @Override
    public NominationDto toModel(Nomination entity) {
        NominationDto nominationDto = NominationDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .build();
        Link selfLink = linkTo(methodOn(NominationController.class).getNomination(nominationDto.getId())).withSelfRel();
        nominationDto.add(selfLink);
        return nominationDto;
    }

    @Override
    public CollectionModel<NominationDto> toCollectionModel(Iterable<? extends Nomination> entities) {
        CollectionModel<NominationDto> nominationDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(NominationController.class).getAllNominations()).withSelfRel();
        nominationDtos.add(selfLink);
        return nominationDtos;
    }

    public CollectionModel<NominationDto> toCollectionModel(Iterable<? extends Nomination> entities, Link link) {
        CollectionModel<NominationDto> nominationDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        nominationDtos.add(link);
        return nominationDtos;
    }
}
