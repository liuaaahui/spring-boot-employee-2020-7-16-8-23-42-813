package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.model.Company;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyMapperTest {
    @Test
    void should_return_company_when_mapper_to_company_given_companyRequest() {
        //given
        CompanyMapper companyMapper = new CompanyMapper();
        CompanyRequest companyRequest = new CompanyRequest(1,"alibaba",1000,new ArrayList<>());

        //when
        Company company = companyMapper.toCompany(companyRequest);

        //then
        assertEquals(companyRequest.getId(), company.getId());
        assertEquals(companyRequest.getCompanyName(), company.getCompanyName());
        assertEquals(companyRequest.getEmployeesNumber(), company.getEmployeesNumber());
        assertEquals(companyRequest.getEmployees(), company.getEmployees());
    }
}
