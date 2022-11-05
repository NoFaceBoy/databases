package ua.lviv.iot.imdb.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.imdb.controller.AwardController;
import ua.lviv.iot.imdb.domain.Award;
import ua.lviv.iot.imdb.dto.AwardDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AwardDtoAssembler implements RepresentationModelAssembler<Award, AwardDto> {
    @Override
    public AwardDto toModel(Award entity) {
        AwardDto awardDto = AwardDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(AwardController.class).getAward(awardDto.getId())).withSelfRel();
        awardDto.add(selfLink);
        return awardDto;
    }

    @Override
    public CollectionModel<AwardDto> toCollectionModel(Iterable<? extends Award> entities) {
        CollectionModel<AwardDto> awardDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(AwardController.class).getAllAwards()).withSelfRel();
        awardDtos.add(selfLink);
        return awardDtos;
    }

    public CollectionModel<AwardDto> toCollectionModel(Iterable<? extends Award> entities, Link link) {
        CollectionModel<AwardDto> awardDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        awardDtos.add(link);
        return awardDtos;
    }
}
