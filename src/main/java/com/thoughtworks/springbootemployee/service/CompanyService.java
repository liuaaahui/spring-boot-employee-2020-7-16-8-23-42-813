package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    CompanyService(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }
    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }
}
