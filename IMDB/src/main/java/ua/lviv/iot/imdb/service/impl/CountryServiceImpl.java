package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.controller.CountryController;
import ua.lviv.iot.imdb.domain.Country;
import ua.lviv.iot.imdb.dto.CountryDto;
import ua.lviv.iot.imdb.exception.CountryNotFoundException;
import ua.lviv.iot.imdb.repository.CountryRepository;
import ua.lviv.iot.imdb.service.CountryService;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepository countryRepository;

    public Country findById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Transactional
    public Country create(Country country) {
        countryRepository.save(country);
        return country;
    }

    @Transactional
    public void update(Integer id, Country uCountry) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        //update
        country.setName(uCountry.getName());
        countryRepository.save(country);
    }

    @Transactional
    public void delete(Integer id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        countryRepository.delete(country);
    }
    @Override
    public CountryDto parametrizedInsertIntoTable(CountryDto countryDto) {
        Integer id = countryRepository.parametrizedInsertIntoTable(countryDto.getName());
        Link selfLink = linkTo(methodOn(CountryController.class).getCountry(id)).withSelfRel();
        countryDto.add(selfLink);
        return countryDto;
    }

    @Override
    public void insertNumberedRecords() { countryRepository.insertNumberedRecords(); }
}

