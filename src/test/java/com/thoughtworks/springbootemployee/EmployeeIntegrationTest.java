package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepository companyRepository;
    private Integer companyId;
    @BeforeEach
    public void before() {
        Company company = new Company(1, "ali");
        Employee employee = new Employee(1, 18, "alex", "male", 1000.0);
        Company saveCompany = companyRepository.save(company);
        employee.setCompanyId(saveCompany.getId());
        companyId = saveCompany.getId();
        employeeRepository.save(employee);
    }

    @AfterEach
    public void after() {
        companyRepository.deleteAll();
        employeeRepository.deleteAll();
        assert companyRepository.findAll().isEmpty();
        assert employeeRepository.findAll().isEmpty();
    }

    @Test
    public void should_return_employees_when_get_employees_given_none() throws Exception {
        //when then
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.data[0].name").value("alex"))
                .andExpect(jsonPath("$.data[0].gender").value("male"));
    }

    @Test
    public void should_return_employee_when_post_employee_given_employee() throws Exception {
        //when then
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"age\":18,\n" +
                        "    \"name\":\"spy\",\n" +
                        "    \"gender\":\"female\",\n" +
                        "    \"companyId\":" + this.companyId + "\n" +
                        "}")).andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.data.name").value("spy"))
                .andExpect(jsonPath("$.data.gender").value("female"));
    }
}
