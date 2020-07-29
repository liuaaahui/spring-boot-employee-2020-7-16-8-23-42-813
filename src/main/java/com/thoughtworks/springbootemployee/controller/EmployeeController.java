package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.data.EmployeeData;
import com.thoughtworks.springbootemployee.entity.ResultBean;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XUAL7
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final String ID_COULD_NOT_BE_SET = "ID could not be set";
    private static final String SUCCESS = "success";
    public static final String EMPLOYEE_NOT_FOUND = "employee not found";
    public static final String CREATION_FAILED = "Creation failed";

    @GetMapping
    public ResultBean<List<Employee>> getEmployees(@PathParam("page") Integer page, @PathParam("pageSize") Integer pageSize, @PathParam("gender") String gender) {
        List<Employee> result = EmployeeData.employees;
        if (gender != null) {
            result = result.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
        }
        if (page == null || pageSize == null) {
            return ResultBean.success(result);
        }
        return ResultBean.success(result.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList()));
    }

    @GetMapping("/{employeeID}")
    public ResultBean<Employee> getEmployee(@PathVariable Integer employeeID) {
        return ResultBean.success(findEmployee(employeeID));
    }

    public Employee findEmployee(Integer ID) {
        return EmployeeData.employees.stream()
                .filter(employee -> employee.getId().equals(ID))
                .findFirst()
                .orElse(EmployeeData.emptyEmployee);
    }

    @PostMapping
    public ResultBean<Employee> addEmployee(@RequestBody Employee employee) {
        if (employee.getId() != null) {
            return ResultBean.error(ResultBean.ERROR_CODE, ID_COULD_NOT_BE_SET);
        }
        boolean success = EmployeeData.addEmployee(employee);
        return success ? ResultBean.success(employee) : ResultBean.error(0, CREATION_FAILED);
    }

    @PutMapping("/{employeeID}")
    public ResultBean<Employee> updateEmployee(@PathVariable Integer employeeID, @RequestBody Employee employee) {
        Employee employeeInDatabase = findEmployee(employeeID);
        if (employeeInDatabase == EmployeeData.emptyEmployee) {
            return ResultBean.error(ResultBean.ERROR_CODE, EMPLOYEE_NOT_FOUND);
        }
        employeeInDatabase = employee;
        return ResultBean.success(employeeInDatabase);
    }

    @DeleteMapping("/{employeeID}")
    public ResultBean<Boolean> deleteEmployee(@PathVariable Integer employeeID) {
        Employee employee = findEmployee(employeeID);
        if (employee == EmployeeData.emptyEmployee) {
            return ResultBean.error(ResultBean.ERROR_CODE, EMPLOYEE_NOT_FOUND);
        }
        EmployeeData.employees.remove(employee);
        return ResultBean.success();
    }

}
