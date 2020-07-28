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

    private static final String ID_COULD_NOT_BE_SET = "ID could not be set";
    private static final String SUCCESS = "success";
    private static final String COMPANY_NOT_FIND = "company not find";

    @GetMapping
    public List<Company> getCompanies(@PathParam("page") Integer page, @PathParam("pageSize") Integer pageSize) {
        if (page == null || pageSize == null) {
            return CompanyData.companies;
        }
        return CompanyData.companies.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    @GetMapping("/{companyID}")
    public Company getCompany(@PathVariable Integer companyID) {
        return findCompany(companyID);
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

    @PutMapping("/{companyID}")
    public String updateCompany(@RequestBody Company companyInfo, @PathVariable Integer companyID) {
        Company companyInDatabase = findCompany(companyID);
        if (companyInDatabase == CompanyData.emptyCompany) {
            return COMPANY_NOT_FIND;
        }
        if (companyInfo.getCompanyName() != null) {
            companyInDatabase.setCompanyName(companyInfo.getCompanyName());
        }
        return SUCCESS;
    }

    @DeleteMapping("/{companyID}")
    public String deleteCompany(@PathVariable Integer companyID) {
        Company company = findCompany(companyID);
        if (company == CompanyData.emptyCompany) {
            return COMPANY_NOT_FIND;
        }
        CompanyData.companies.remove(company);
        return SUCCESS;
    }

    public Company findCompany(Integer ID) {
        return CompanyData.companies.stream()
                .filter(company -> company.getId().equals(ID))
                .findFirst()
                .orElse(CompanyData.emptyCompany);
    }
}
