package ua.lviv.iot.imdb.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.imdb.controller.CompanyController;
import ua.lviv.iot.imdb.domain.Company;
import ua.lviv.iot.imdb.dto.CompanyDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CompanyDtoAssembler implements RepresentationModelAssembler<Company, CompanyDto> {
    @Override
    public CompanyDto toModel(Company entity) {
        CompanyDto companyDto = CompanyDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(CompanyController.class).getCompany(companyDto.getId())).withSelfRel();
        companyDto.add(selfLink);
        return companyDto;
    }

    @Override
    public CollectionModel<CompanyDto> toCollectionModel(Iterable<? extends Company> entities) {
        CollectionModel<CompanyDto> companyDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CompanyController.class).getAllCompanies()).withSelfRel();
        companyDtos.add(selfLink);
        return companyDtos;
    }

    public CollectionModel<CompanyDto> toCollectionModel(Iterable<? extends Company> entities, Link link) {
        CollectionModel<CompanyDto> companyDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        companyDtos.add(link);
        return companyDtos;
    }
}
