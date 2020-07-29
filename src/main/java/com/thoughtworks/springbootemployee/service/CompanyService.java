package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }

    public List<Company> getCompanies(Integer page, Integer pageSize) {
        return companyRepository.getCompanies().stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    public Company getCompany(Integer id) {
        return companyRepository.getCompanies().stream().filter(company -> company.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Employee> getEmployees(Integer id) {
        return companyRepository.getCompanies().stream().filter(company -> company.getId().equals(id)).findFirst().orElse(null).getEmployees();
    }

    public Company addCompany(Company company) {
        company.setId(companyRepository.getCompanies().size() + 1);
        companyRepository.getCompanies().add(company);
        return company;
    }

    public Company updateCompany(Integer id, Company updatedCompany) {
        Company company = getCompany(id);
        company.setCompanyName(updatedCompany.getCompanyName());
        return company;
    }

    public Company deleteCompany(Integer id) {
        Company company = getCompany(id);
        companyRepository.getCompanies().remove(company);
        return company;
    }
}
