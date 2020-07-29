package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    public static List<Employee> employees = new ArrayList<>();
    public static Employee emptyEmployee;
    private static transient int EmployeeIDCount;

    public static final int DEFAULT_COMPANY_COUNT = 1004;

     {
        employees.add(new Employee(1001, 18, "zach", "male", 1000.0));
        employees.add(new Employee(1002, 17, "alex", "male", 1000.0));
        employees.add(new Employee(1003, 19, "zach1", "female", 1000.0));
        employees.add(new Employee(1004, 18, "zach2", "female", 1000.0));
        emptyEmployee = new Employee();
        EmployeeIDCount = DEFAULT_COMPANY_COUNT;
    }

    public static boolean addEmployee(Employee employee) {
        employee.setId(++EmployeeIDCount);
        return employees.add(employee);

    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
