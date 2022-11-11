package ua.lviv.iot.imdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.imdb.domain.Company;
import ua.lviv.iot.imdb.dto.CompanyDto;
import ua.lviv.iot.imdb.dto.assembler.CompanyDtoAssembler;
import ua.lviv.iot.imdb.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDtoAssembler companyDtoAssembler;

    @GetMapping(value = "/{companyId}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable Integer companyId) {
        Company company = companyService.findById(companyId);
        CompanyDto companyDto = companyDtoAssembler.toModel(company);
        return new ResponseEntity<>(companyDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CompanyDto>> getAllCompanies() {
        List<Company> companies = companyService.findAll();
        CollectionModel<CompanyDto> companyDtos = companyDtoAssembler.toCollectionModel(companies);
        return new ResponseEntity<>(companyDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CompanyDto> addCompany(@RequestBody Company company) {
        Company newCompany = companyService.create(company);
        CompanyDto companyDto = companyDtoAssembler.toModel(newCompany);
        return new ResponseEntity<>(companyDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{companyId}")
    public ResponseEntity<?> updateCompany(@RequestBody Company uCompany, @PathVariable Integer companyId) {
        companyService.update(companyId, uCompany);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable Integer companyId) {
        companyService.delete(companyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
