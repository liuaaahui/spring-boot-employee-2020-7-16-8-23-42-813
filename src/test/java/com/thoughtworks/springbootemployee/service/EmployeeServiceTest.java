package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {

    @Test
    public void should_return_employees_when_get_employees_given_none() {
        //given
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, 18, "hello", "male", 1000.0));
        employees.add(new Employee(2, 18, "hellome", "male", 1000.0));
        given(employeeRepository.getEmployees()).willReturn(employees);
        //when
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employeeList = employeeService.getEmployees();
        //then
        assertIterableEquals(employeeList, employees);
    }

    @Test
    void should_return_employees_when_get_employees_given_page_pageSize() {
        //given
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, 18, "hello", "male", 1000.0));
        employees.add(new Employee(2, 18, "hellome", "male", 1000.0));
        employees.add(new Employee(3, 18, "hellome2", "male", 1000.0));
        employees.add(new Employee(4, 18, "hellome3", "female", 2000.0));
        given(employeeRepository.getEmployees()).willReturn(employees);
        //when
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employeeList = employeeService.getEmployees(1, 2);
        //then
        assertIterableEquals(employeeList, employees.subList(0, 2));

    }
}
