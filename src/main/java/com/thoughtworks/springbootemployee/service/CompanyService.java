package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    CompanyService(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }
    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }

    public List<Company> getCompanies(Integer page, Integer pageSize) {
        return companyRepository.getCompanies().stream().skip((page-1)*pageSize).limit(pageSize).collect(Collectors.toList());
    }
}
