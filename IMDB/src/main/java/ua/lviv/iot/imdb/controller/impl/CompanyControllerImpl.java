package ua.lviv.iot.imdb.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.controller.CompanyController;
import ua.lviv.iot.imdb.domain.Company;
import ua.lviv.iot.imdb.service.CompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyControllerImpl implements CompanyController {
    @Autowired
    CompanyService companyService;

    @Override
    public List<Company> findAll() {
        return companyService.findAll();
    }

    @Override
    public Optional<Company> findById(Integer id) {
        return companyService.findById(id);
    }

    @Override
    public int create(Company company) {
        return companyService.create(company);
    }

    @Override
    public int update(Integer id, Company company) {
        return companyService.update(id, company);
    }

    @Override
    public int delete(Integer id) {
        return companyService.delete(id);
    }
}
