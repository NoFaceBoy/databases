package ua.lviv.iot.imdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.imdb.dao.CompanyDao;
import ua.lviv.iot.imdb.domain.Company;
import ua.lviv.iot.imdb.service.CompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public Optional<Company> findById(Integer id) {
        return companyDao.findById(id);
    }

    @Override
    public int create(Company company) {
        return companyDao.create(company);
    }

    @Override
    public int update(Integer id, Company company) {
        return companyDao.update(id, company);
    }

    @Override
    public int delete(Integer id) {
        return companyDao.delete(id);
    }
}
