package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.domain.Company;
import ua.lviv.iot.imdb.exception.CompanyNotFoundException;
import ua.lviv.iot.imdb.repository.CompanyRepository;
import ua.lviv.iot.imdb.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public Company findById(Integer id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    public Company create(Company company) {
        companyRepository.save(company);
        return company;
    }

    @Transactional
    public void update(Integer id, Company uCompany) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        //update
        company.setName(uCompany.getName());
        companyRepository.save(company);
    }

    @Transactional
    public void delete(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        companyRepository.delete(company);
    }
}

