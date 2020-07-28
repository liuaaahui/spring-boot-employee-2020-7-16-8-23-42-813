package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.data.EmployeeData;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final String ID_COULD_NOT_BE_SET = "ID could not be set";
    private static final String SUCCESS = "success";
    public static final String EMPLOYEE_NOT_FOUND = "employee not found";

    @GetMapping
    public List<Employee> getEmployees(@PathParam("page") Integer page, @PathParam("pageSize") Integer pageSize, @PathParam("gender") String gender) {
        List<Employee> result = EmployeeData.employees;
        if (gender != null) {
            result = result.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
        }
        if (page == null || pageSize == null) {
            return result;
        }
        return result.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    @GetMapping("/{employeeID}")
    public Employee getEmployee(@PathVariable Integer employeeID) {
        return findEmployee(employeeID);
    }

    public Employee findEmployee(Integer ID) {
        return EmployeeData.employees.stream()
                .filter(employee -> employee.getId().equals(ID))
                .findFirst()
                .orElse(EmployeeData.emptyEmployee);
    }

    @PostMapping
    public String addEmployee(@RequestBody Employee employee) {
        if (employee.getId() != null) {
            return ID_COULD_NOT_BE_SET;
        }
        EmployeeData.addEmployee(employee);
        return SUCCESS;
    }

    @PutMapping("/{employeeID}")
    public String updateEmployee(@PathVariable Integer employeeID, @RequestBody Employee employee) {
        Employee employeeInDatabase = findEmployee(employeeID);
        if (employeeInDatabase == EmployeeData.emptyEmployee) {
            return EMPLOYEE_NOT_FOUND;
        }
        updateAttributeOfEmployee(employeeInDatabase, employee);
        return SUCCESS;
    }

    public void updateAttributeOfEmployee(Employee employeeInDatabase, Employee employee) {
        if (employee.getName() != null) {
            employeeInDatabase.setName(employee.getName());
        }
        if (employee.getGender() != null) {
            employeeInDatabase.setGender(employee.getGender());
        }
        if (employee.getAge() != null) {
            employeeInDatabase.setAge(employee.getAge());
        }
        if (employee.getSalary() != null) {
            employeeInDatabase.setSalary(employee.getSalary());
        }

    }
}
