package com.thoughtworks.springbootemployee.data;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    public static List<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee(1001, 18, "zach", "male", 1000));
        employees.add(new Employee(1002, 17, "alex", "male", 1000));
        employees.add(new Employee(1003, 19, "zach1", "female", 1000));
        employees.add(new Employee(1004, 18, "zach2", "female", 1000));
    }


}
