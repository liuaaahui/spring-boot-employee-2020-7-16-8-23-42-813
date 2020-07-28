package com.thoughtworks.springbootemployee.model;

import java.util.List;

public class Company {
    private int id;
    private String companyName;
    private List<Employee> employees;
    private int empoyeesNumber = 0;

    public Company(int id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        this.empoyeesNumber = this.employees.size();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
