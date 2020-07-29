package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.entity.ResultBean;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XUAL7
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private static final String ID_COULD_NOT_BE_SET = "ID could not be set";
    private static final String SUCCESS = "success";
    private static final String COMPANY_NOT_FIND = "company not find";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<List<Company>> getCompanies(@PathParam("page") Integer page, @PathParam("pageSize") Integer pageSize) {
        if (page == null || pageSize == null) {
            return ResultBean.success(CompanyRepository.companies);
        }
        return ResultBean.success(CompanyRepository.companies.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList()));
    }

    @GetMapping("/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<Company> getCompany(@PathVariable Integer companyID) {
        return ResultBean.success(findCompany(companyID));
    }

    @GetMapping("/{companyID}/employees")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<List<Employee>> getEmployee(@PathVariable Integer companyID) {
        return ResultBean.success(CompanyRepository.companies.stream()
                .filter(company -> company.getId().equals(companyID))
                .findFirst()
                .orElse(CompanyRepository.emptyCompany).getEmployees());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultBean<Company> addCompany(@RequestBody Company company) {
        if (company.getId() != null) {
            return ResultBean.error(ResultBean.ERROR_CODE, ID_COULD_NOT_BE_SET);
        }
        CompanyRepository.addCompany(company);
        return ResultBean.success(company);
    }

    @PutMapping("/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<Company> updateCompany(@RequestBody Company companyInfo, @PathVariable Integer companyID) {
        Company companyInDatabase = findCompany(companyID);
        if (companyInDatabase == CompanyRepository.emptyCompany) {
            return ResultBean.error(ResultBean.ERROR_CODE, COMPANY_NOT_FIND);
        }
        if (companyInfo.getCompanyName() != null) {
            companyInDatabase.setCompanyName(companyInfo.getCompanyName());
        }
        return ResultBean.success(companyInDatabase);
    }

    @DeleteMapping("/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<Boolean> deleteCompany(@PathVariable Integer companyID) {
        Company company = findCompany(companyID);
        if (company == CompanyRepository.emptyCompany) {
            return ResultBean.error(ResultBean.ERROR_CODE, COMPANY_NOT_FIND);
        }
        CompanyRepository.companies.remove(company);
        return ResultBean.success();
    }

    public Company findCompany(Integer ID) {
        return CompanyRepository.companies.stream()
                .filter(company -> company.getId().equals(ID))
                .findFirst()
                .orElse(CompanyRepository.emptyCompany);
    }
}
