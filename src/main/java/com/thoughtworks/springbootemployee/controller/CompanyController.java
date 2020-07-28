package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.data.CompanyData;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @GetMapping
    public List<Company> getCompanies() {
        return CompanyData.companies;
    }

    @GetMapping("/{companyID}")
    public Company getCompany(@PathVariable Integer companyID) {
        return CompanyData.companies.stream()
                .filter(company -> company.getId().equals(companyID))
                .findFirst()
                .orElse(CompanyData.emptyCompany);
    }

    @GetMapping("/{companyID}/employees")
    public List<Employee> getEmployee(@PathVariable Integer companyID) {
        return CompanyData.companies.stream()
                .filter(company -> company.getId().equals(companyID))
                .findFirst()
                .orElse(CompanyData.emptyCompany).getEmployees();
    }
}
