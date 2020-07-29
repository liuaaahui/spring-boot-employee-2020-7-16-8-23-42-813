package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.data.CompanyData;
import com.thoughtworks.springbootemployee.entity.ResultBean;
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
    public ResultBean<List<Company>> getCompanies(@PathParam("page") Integer page, @PathParam("pageSize") Integer pageSize) {
        if (page == null || pageSize == null) {
            return ResultBean.success(CompanyData.companies);
        }
        return ResultBean.success(CompanyData.companies.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList()));
    }

    @GetMapping("/{companyID}")
    public ResultBean<Company> getCompany(@PathVariable Integer companyID) {
        return ResultBean.success(findCompany(companyID));
    }

    @GetMapping("/{companyID}/employees")
    public ResultBean<List<Employee>> getEmployee(@PathVariable Integer companyID) {
        return ResultBean.success(CompanyData.companies.stream()
                .filter(company -> company.getId().equals(companyID))
                .findFirst()
                .orElse(CompanyData.emptyCompany).getEmployees());
    }

    @PostMapping
    public ResultBean<Company> addCompany(@RequestBody Company company) {
        if (company.getId() != null) {
            return ResultBean.error(ResultBean.ERROR_CODE, ID_COULD_NOT_BE_SET);
        }
        CompanyData.addCompany(company);
        return ResultBean.success(company);
    }

    @PutMapping("/{companyID}")
    public ResultBean<Company> updateCompany(@RequestBody Company companyInfo, @PathVariable Integer companyID) {
        Company companyInDatabase = findCompany(companyID);
        if (companyInDatabase == CompanyData.emptyCompany) {
            return ResultBean.error(ResultBean.ERROR_CODE, COMPANY_NOT_FIND);
        }
        if (companyInfo.getCompanyName() != null) {
            companyInDatabase.setCompanyName(companyInfo.getCompanyName());
        }
        return ResultBean.success(companyInDatabase);
    }

    @DeleteMapping("/{companyID}")
    public ResultBean<Boolean> deleteCompany(@PathVariable Integer companyID) {
        Company company = findCompany(companyID);
        if (company == CompanyData.emptyCompany) {
            return ResultBean.error(ResultBean.ERROR_CODE, COMPANY_NOT_FIND);
        }
        CompanyData.companies.remove(company);
        return ResultBean.success();
    }

    public Company findCompany(Integer ID) {
        return CompanyData.companies.stream()
                .filter(company -> company.getId().equals(ID))
                .findFirst()
                .orElse(CompanyData.emptyCompany);
    }
}
