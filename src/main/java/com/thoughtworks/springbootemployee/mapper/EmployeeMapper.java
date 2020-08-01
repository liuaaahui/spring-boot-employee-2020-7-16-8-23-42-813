package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.Employee;

public class EmployeeMapper {
    public Employee toEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setGender(employeeRequest.getGender());
        employee.setAge(employeeRequest.getAge());
        employee.setId(employeeRequest.getId());
        employee.setSalary(employeeRequest.getSalary());
        employee.setCompanyId(employeeRequest.getCompanyId());
        return employee;
    }

}
