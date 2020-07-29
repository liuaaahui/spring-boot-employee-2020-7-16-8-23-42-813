package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.ResultBean;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author XUAL7
 */
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private static final String ID_COULD_NOT_BE_SET = "ID could not be set";
    private static final String SUCCESS = "success";
    private static final String COMPANY_NOT_FIND = "company not find";
    public static final String NOT_EXIST = "not exist";
    private final CompanyService companyService;

    @Autowired
    CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<List<Company>> getCompanies(@PathParam("page") Integer page, @PathParam("pageSize") Integer pageSize) {
        if (page == null || pageSize == null) {
            return ResultBean.success(companyService.getCompanies());
        }
        return ResultBean.success(companyService.getCompanies(page, pageSize).getContent());
    }

    @GetMapping("/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<Company> getCompany(@PathVariable Integer companyID) {
        return ResultBean.success(companyService.getCompany(companyID));
    }

    @GetMapping("/{companyID}/employees")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<List<Employee>> getEmployee(@PathVariable Integer companyID) {
        return ResultBean.success(companyService.getEmployees(companyID));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultBean<Company> addCompany(@RequestBody Company company) {
        if (company.getId() != null) {
            return ResultBean.error(ResultBean.ERROR_CODE, ID_COULD_NOT_BE_SET);
        }
        companyService.addCompany(company);
        return ResultBean.success(company);
    }

    @PutMapping("/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<Company> updateCompany(@RequestBody Company companyInfo, @PathVariable Integer companyID) {
        return ResultBean.success(companyService.updateCompany(companyID, companyInfo));
    }

    @DeleteMapping("/{companyID}")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<Company> deleteCompany(@PathVariable Integer companyID) {
        Company company = companyService.deleteCompany(companyID);
        if (company == null) {
            return ResultBean.error(0, NOT_EXIST);
        }
        return ResultBean.success(company);
    }
}
