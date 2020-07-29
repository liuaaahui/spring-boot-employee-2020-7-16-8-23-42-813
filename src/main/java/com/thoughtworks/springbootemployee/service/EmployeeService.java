package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    public List<Employee> getEmployees(Integer page, Integer pageSize, List<Employee> employeesList) {
        return employeesList == null ? employeeRepository.getEmployees().stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList())
                : employeesList.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    public List<Employee> getEmployees(String gender) {
        return employeeRepository.getEmployees().stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee getEmployee(Integer id) {
        return employeeRepository.getEmployees().stream().filter(employee -> employee.getId().equals(id)).findFirst().orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        employee.setId(employeeRepository.getEmployees().size());
        employeeRepository.getEmployees().add(employee);
        return employee;
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Employee employee = this.getEmployee(id);
        employee.setName(updatedEmployee.getName());
        employee.setAge(updatedEmployee.getAge());
        employee.setGender(updatedEmployee.getGender());
        employee.setSalary(updatedEmployee.getSalary());
        return employee;
    }

    public Employee delete(Integer id) {
        Employee employee = getEmployee(id);
        employeeRepository.getEmployees().remove(employee);
        return employee;
    }
}
