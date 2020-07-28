package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.data.EmployeeData;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
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
}
