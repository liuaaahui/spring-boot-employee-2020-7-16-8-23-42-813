package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.data.CompanyData;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    public static final String ID_COULD_NOT_BE_SET = "ID could not be set";
    public static final String SUCCESS = "success";

    @GetMapping
    public List<Company> getCompanies(@PathParam("page") Integer page, @PathParam("pageSize") Integer pageSize) {
        if (page == null || pageSize == null) {
            return CompanyData.companies;
        }
        return CompanyData.companies.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
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

    @PostMapping
    public String addCompany(@RequestBody Company company) {
        if (company.getId() != null) {
            return ID_COULD_NOT_BE_SET;
        }
        CompanyData.addCompany(company);
        return SUCCESS;
    }
}
