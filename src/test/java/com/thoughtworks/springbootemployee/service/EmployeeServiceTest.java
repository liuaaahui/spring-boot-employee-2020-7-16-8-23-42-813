package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        List<Employee> employeeList = employeeService.getEmployees(1, 2,null);
        //then
        assertIterableEquals(employeeList, employees.subList(0, 2));
    }

    @Test
    void should_return_employees_when_get_employees_given_gender() {
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
        List<Employee> employeeList = employeeService.getEmployees("male");
        //then
        assertIterableEquals(employeeList, employees.subList(0, 3));
    }

    @Test
    void should_return_employee_when_get_employee_given_id() {
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
        Employee employee = employeeService.getEmployee(1);
        //then
        assertEquals(employees.get(0), employee);
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() {
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
        Employee employee = employeeService.addEmployee(new Employee(23, 18, "alex", "male", 121341564.0));
        //then
        assertEquals(employees.get(employees.size() - 1), employee);
    }

    @Test
    void should_return_employee_when_update_employee_given_employee() {
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
        Employee employee = employeeService.updateEmployee(1, new Employee(23, 18, "alex", "female", 1000.0));
        //then
        assertEquals(employees.get(0).getAge(), employee.getAge());
        assertEquals(employees.get(0).getName(), employee.getName());
        assertEquals(employees.get(0).getGender(), employee.getGender());
    }

    @Test
    void should_return_employee_when_delete_employee_given_employee_id() {
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
        Employee employee = employeeService.delete(1);
        //then
        assertFalse(employees.stream().anyMatch(staff -> staff.getId().equals(1)));
    }
}
