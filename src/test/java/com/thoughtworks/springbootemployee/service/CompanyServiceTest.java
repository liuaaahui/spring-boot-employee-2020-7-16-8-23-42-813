//package com.thoughtworks.springbootemployee.service;
//
//import com.thoughtworks.springbootemployee.model.Company;
//import com.thoughtworks.springbootemployee.model.Employee;
//import com.thoughtworks.springbootemployee.repository.CompanyRepository;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.mock;
//
//public class CompanyServiceTest {
//    @Test
//    public void should_return_companies_when_get_companies_then_given_none() {
//        //given
//        CompanyRepository employeeRepository = mock(CompanyRepository.class);
//        List<Company> companies = new ArrayList<>();
//        ArrayList<Employee> employeesOfOOCL = new ArrayList<>();
//        ArrayList<Employee> employeesOfBlibli = new ArrayList<>();
//        companies.add(new Company(1, "oocl"));
//        companies.add(new Company(2, "blibli"));
//        employeesOfOOCL.add(new Employee(1001, 18, "zach", "male", 1000.0));
//        employeesOfOOCL.add(new Employee(1002, 17, "alex", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1003, 19, "zach1", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1004, 18, "zach2", "male", 1000.0));
//
//        companies.get(0).setEmployees(employeesOfOOCL);
//        companies.get(1).setEmployees(employeesOfBlibli);
//        given(employeeRepository.findAll()).willReturn(companies);
////        when
//        CompanyService companyService = new CompanyService(employeeRepository);
//        List<Company> foundCompanies = companyService.getCompanies();
//
////        then
//        assertIterableEquals(companies, foundCompanies);
//    }
//
//    @Test
//    public void should_return_companies_when_get_companies_then_given_pages() {
//        //given
//        CompanyRepository employeeRepository = mock(CompanyRepository.class);
//        List<Company> companies = new ArrayList<>();
//        ArrayList<Employee> employeesOfOOCL = new ArrayList<>();
//        ArrayList<Employee> employeesOfBlibli = new ArrayList<>();
//        companies.add(new Company(1, "oocl"));
//        companies.add(new Company(2, "blibli"));
//        employeesOfOOCL.add(new Employee(1001, 18, "zach", "male", 1000.0));
//        employeesOfOOCL.add(new Employee(1002, 17, "alex", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1003, 19, "zach1", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1004, 18, "zach2", "male", 1000.0));
//
//        companies.get(0).setEmployees(employeesOfOOCL);
//        companies.get(1).setEmployees(employeesOfBlibli);
//        given(employeeRepository.findAll()).willReturn(companies);
////        when
//        CompanyService companyService = new CompanyService(employeeRepository);
//        List<Company> foundCompanies = companyService.getCompanies(2, 1).getContent();
//
////        then
//        assertIterableEquals(companies.subList(1, 2), foundCompanies);
//    }
//
//
//    @Test
//    public void should_return_company_when_get_company_then_given_id() {
//        //given
//        CompanyRepository employeeRepository = mock(CompanyRepository.class);
//        List<Company> companies = new ArrayList<>();
//        ArrayList<Employee> employeesOfOOCL = new ArrayList<>();
//        ArrayList<Employee> employeesOfBlibli = new ArrayList<>();
//        companies.add(new Company(1, "oocl"));
//        companies.add(new Company(2, "blibli"));
//        employeesOfOOCL.add(new Employee(1001, 18, "zach", "male", 1000.0));
//        employeesOfOOCL.add(new Employee(1002, 17, "alex", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1003, 19, "zach1", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1004, 18, "zach2", "male", 1000.0));
//
//        companies.get(0).setEmployees(employeesOfOOCL);
//        companies.get(1).setEmployees(employeesOfBlibli);
//        given(employeeRepository.findAll()).willReturn(companies);
////        when
//        CompanyService companyService = new CompanyService(employeeRepository);
//        Company foundCompany = companyService.getCompany(1);
//
////        then
//        assertEquals(companies.get(0), foundCompany);
//    }
//
//    @Test
//    public void should_return_employee_when_get_company_employee_then_given_id() {
//        //given
//        CompanyRepository employeeRepository = mock(CompanyRepository.class);
//        List<Company> companies = new ArrayList<>();
//        ArrayList<Employee> employeesOfOOCL = new ArrayList<>();
//        ArrayList<Employee> employeesOfBlibli = new ArrayList<>();
//        companies.add(new Company(1, "oocl"));
//        companies.add(new Company(2, "blibli"));
//        employeesOfOOCL.add(new Employee(1001, 18, "zach", "male", 1000.0));
//        employeesOfOOCL.add(new Employee(1002, 17, "alex", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1003, 19, "zach1", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1004, 18, "zach2", "male", 1000.0));
//
//        companies.get(0).setEmployees(employeesOfOOCL);
//        companies.get(1).setEmployees(employeesOfBlibli);
//        given(employeeRepository.findAll()).willReturn(companies);
////        when
//        CompanyService companyService = new CompanyService(employeeRepository);
//        List<Employee> employees = companyService.getEmployees(1);
//
////        then
//        assertIterableEquals(companies.get(0).getEmployees(), employees);
//    }
//
//
//    @Test
//    public void should_return_company_when_add_company_then_given_company() {
//        //given
//        CompanyRepository employeeRepository = mock(CompanyRepository.class);
//        List<Company> companies = new ArrayList<>();
//        ArrayList<Employee> employeesOfOOCL = new ArrayList<>();
//        ArrayList<Employee> employeesOfBlibli = new ArrayList<>();
//        companies.add(new Company(1, "oocl"));
//        companies.add(new Company(2, "blibli"));
//        employeesOfOOCL.add(new Employee(1001, 18, "zach", "male", 1000.0));
//        employeesOfOOCL.add(new Employee(1002, 17, "alex", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1003, 19, "zach1", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1004, 18, "zach2", "male", 1000.0));
//
//        companies.get(0).setEmployees(employeesOfOOCL);
//        companies.get(1).setEmployees(employeesOfBlibli);
//        given(employeeRepository.findAll()).willReturn(companies);
////        when
//        CompanyService companyService = new CompanyService(employeeRepository);
//        Company company = companyService.addCompany(new Company(3, "huawei"));
//
////        then
//        assertEquals(companies.stream().filter(addedCompany -> addedCompany.getId().equals(3)).findFirst().orElse(null), company);
//    }
//
//    @Test
//    public void should_return_company_when_update_company_then_given_company() {
//        //given
//        CompanyRepository employeeRepository = mock(CompanyRepository.class);
//        List<Company> companies = new ArrayList<>();
//        ArrayList<Employee> employeesOfOOCL = new ArrayList<>();
//        ArrayList<Employee> employeesOfBlibli = new ArrayList<>();
//        companies.add(new Company(1, "oocl"));
//        companies.add(new Company(2, "blibli"));
//        employeesOfOOCL.add(new Employee(1001, 18, "zach", "male", 1000.0));
//        employeesOfOOCL.add(new Employee(1002, 17, "alex", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1003, 19, "zach1", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1004, 18, "zach2", "male", 1000.0));
//
//        companies.get(0).setEmployees(employeesOfOOCL);
//        companies.get(1).setEmployees(employeesOfBlibli);
//        given(employeeRepository.findAll()).willReturn(companies);
////        when
//        CompanyService companyService = new CompanyService(employeeRepository);
//        Company company = companyService.updateCompany(2, new Company(33333, "huawei"));
//
////        then
//        assertEquals(companies.stream().filter(addedCompany -> addedCompany.getId().equals(2)).findFirst().orElse(null), company);
//    }
//
//    @Test
//    public void should_return_company_when_delete_company_then_given_id() {
//        //given
//        CompanyRepository employeeRepository = mock(CompanyRepository.class);
//        List<Company> companies = new ArrayList<>();
//        ArrayList<Employee> employeesOfOOCL = new ArrayList<>();
//        ArrayList<Employee> employeesOfBlibli = new ArrayList<>();
//        companies.add(new Company(1, "oocl"));
//        companies.add(new Company(2, "blibli"));
//        employeesOfOOCL.add(new Employee(1001, 18, "zach", "male", 1000.0));
//        employeesOfOOCL.add(new Employee(1002, 17, "alex", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1003, 19, "zach1", "male", 1000.0));
//        employeesOfBlibli.add(new Employee(1004, 18, "zach2", "male", 1000.0));
//
//        companies.get(0).setEmployees(employeesOfOOCL);
//        companies.get(1).setEmployees(employeesOfBlibli);
//        given(employeeRepository.findAll()).willReturn(companies);
////        when
//        CompanyService companyService = new CompanyService(employeeRepository);
//        Company company = companyService.deleteCompany(1);
//
////        then
//        assertNull(companies.stream().filter(addedCompany -> addedCompany.getId().equals(1)).findFirst().orElse(null));
//    }
//}
