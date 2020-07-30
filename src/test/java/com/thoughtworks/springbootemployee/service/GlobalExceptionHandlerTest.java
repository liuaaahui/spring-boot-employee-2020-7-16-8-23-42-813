package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GlobalExceptionHandlerTest {
    @Test
    void should_return_not_found_exception_when_find_by_id_given_id() {
        //given
        Integer id = 1;
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        given(employeeRepository.findById(anyInt())).willReturn(null);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        //when
        Exception notFoundException = assertThrows(NotFoundException.class, () -> employeeService.getEmployee(id));
        //then
        assertEquals(notFoundException.getClass(), NotFoundException.class);
    }
}
