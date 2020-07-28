package com.thoughtworks.springbootemployee.data;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    public static List<Employee> employees = new ArrayList<>();
    public static Employee emptyEmployee;
    private static transient int EmployeeIDCount;

    public static final int DEFAULT_COMPANY_COUNT = 4;

    static {
        employees.add(new Employee(1001, 18, "zach", "male", 1000.0));
        employees.add(new Employee(1002, 17, "alex", "male", 1000.0));
        employees.add(new Employee(1003, 19, "zach1", "female", 1000.0));
        employees.add(new Employee(1004, 18, "zach2", "female", 1000.0));
        emptyEmployee = new Employee();
        EmployeeIDCount = DEFAULT_COMPANY_COUNT;
    }

    public static void addEmployee(Employee employee) {
        employee.setId(++EmployeeIDCount);
        employees.add(employee);
    }
}
