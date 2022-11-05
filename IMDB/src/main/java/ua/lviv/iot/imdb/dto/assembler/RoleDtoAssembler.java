package ua.lviv.iot.imdb.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.imdb.controller.RoleController;
import ua.lviv.iot.imdb.domain.Role;
import ua.lviv.iot.imdb.dto.RoleDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RoleDtoAssembler implements RepresentationModelAssembler<Role, RoleDto> {
    @Override
    public RoleDto toModel(Role entity) {
        RoleDto roleDto = RoleDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(RoleController.class).getRole(roleDto.getId())).withSelfRel();
        roleDto.add(selfLink);
        return roleDto;
    }

    @Override
    public CollectionModel<RoleDto> toCollectionModel(Iterable<? extends Role> entities) {
        CollectionModel<RoleDto> roleDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RoleController.class).getAllRoles()).withSelfRel();
        roleDtos.add(selfLink);
        return roleDtos;
    }

    public CollectionModel<RoleDto> toCollectionModel(Iterable<? extends Role> entities, Link link) {
        CollectionModel<RoleDto> roleDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        roleDtos.add(link);
        return roleDtos;
    }
}
