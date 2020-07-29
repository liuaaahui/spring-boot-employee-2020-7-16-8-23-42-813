package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Page<Company> getCompanies(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Company getCompany(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    public List<Employee> getEmployees(Integer id) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) {
            return null;
        }
        return company.getEmployees();
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Integer id, Company updatedCompany) {
        updatedCompany.setId(id);
        return companyRepository.save(updatedCompany);
    }

    public Company deleteCompany(Integer id) {
        Company company = getCompany(id);
        if (company == null) {
            return null;
        }
        companyRepository.deleteById(id);
        return company;
    }
}
