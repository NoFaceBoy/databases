package ua.lviv.iot.imdb.service;

import ua.lviv.iot.imdb.domain.Country;
import ua.lviv.iot.imdb.dto.CountryDto;

import java.util.List;

public interface CountryService extends GeneralService<Country, Integer> {
    CountryDto parametrizedInsertIntoTable(CountryDto countryDto);
    void insertNumberedRecords();
}

