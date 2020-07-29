package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    public static List<Company> companies = new ArrayList<>();
    public static Company emptyCompany;
    private static transient int CompanyIDCount;

    public static final int DEFAULT_COMPANY_COUNT = 2;

    static {
        companies.add(new Company(1, "oocl"));
        companies.add(new Company(2, "blibli"));

        ArrayList<Employee> employeesOfOOCL = new ArrayList<>();
        ArrayList<Employee> employeesOfBlibli = new ArrayList<>();

        employeesOfOOCL.add(new Employee(1001, 18, "zach", "male", 1000.0));
        employeesOfOOCL.add(new Employee(1002, 17, "alex", "male", 1000.0));
        employeesOfBlibli.add(new Employee(1003, 19, "zach1", "male", 1000.0));
        employeesOfBlibli.add(new Employee(1004, 18, "zach2", "male", 1000.0));

        companies.get(0).setEmployees(employeesOfOOCL);
        companies.get(1).setEmployees(employeesOfBlibli);
        emptyCompany = new Company(null, null);
        CompanyIDCount = DEFAULT_COMPANY_COUNT;
    }

    public static synchronized void addCompany(Company company) {
        company.setId(++CompanyIDCount);
        companies.add(company);
    }
}
