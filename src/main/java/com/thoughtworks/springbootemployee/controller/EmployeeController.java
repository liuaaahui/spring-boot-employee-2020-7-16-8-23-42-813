package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.ResultBean;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

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
    public static final String NOT_EXIST = "not exist";
    public final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<List<Employee>> getEmployees(@PathParam("page") Integer page, @PathParam("pageSize") Integer pageSize, @PathParam("gender") String gender) {
        List<Employee> result = gender == null ? null : employeeService.getEmployees(gender);
        if (result == null) {
            result = employeeService.getEmployees();
        }
        return ResultBean.success((page == null || pageSize == null) ? result : employeeService.getEmployees(page - 1, pageSize).getContent());
    }

    @GetMapping("/{employeeID}")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<Employee> getEmployee(@PathVariable Integer employeeID) {
        return ResultBean.success(employeeService.getEmployee(employeeID));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultBean<Employee> addEmployee(@RequestBody Employee employee) {
        return ResultBean.success(employeeService.addEmployee(employee));
    }

    @PutMapping("/{employeeID}")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<Employee> updateEmployee(@PathVariable Integer employeeID, @RequestBody Employee employee) {
        return ResultBean.success(employeeService.updateEmployee(employeeID, employee));
    }

    @DeleteMapping("/{employeeID}")
    @ResponseStatus(HttpStatus.OK)
    public ResultBean<Employee> deleteEmployee(@PathVariable Integer employeeID) {
        Employee employee = employeeService.delete(employeeID);
        if (employee == null) {
            return ResultBean.error(0, NOT_EXIST);
        }
        return ResultBean.success(employee);
    }

}
