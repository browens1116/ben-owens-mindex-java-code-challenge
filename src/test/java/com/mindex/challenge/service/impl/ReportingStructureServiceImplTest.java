package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

/**
 * Class for testing ReportingStructureServiceImpl
 * @author Ben Owens
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    /**
     * URL for requesting a reporting structure
     */
    private String reportingStructureUrl;

    /**
     * UIRL for creating an employee
     */
    private String employeeUrl;
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingStructureUrl = "http://localhost:" + port + "/reporting_structure/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
    }

    @Test
    public void testCreateEmployeeWithNoReports() {
        //Test for Employee with no reporting employees

        //Initalize test employee
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        //Create employee using POST request to generate employeeId
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        //Create ReportingStructure using GET request via createdEmployee ID
        ReportingStructure createdReportingStructure = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, createdEmployee.getEmployeeId()).getBody();

        //Assert ReportingStructure is not null
        assertNotNull(createdReportingStructure);
        //Assert ReportingStrucure employee is equivalent to test employee
        assertEmployeeEquivalence(testEmployee, createdReportingStructure.getEmployee());
        //Assert there are no reporting employees
        assertEquals(0, createdReportingStructure.getNumberOfReports());
    }

    @Test
    public void testCreateEmployeeWithReports() {
         //Test for employee with direct and indirect reports

        //Initialize indirect reporting employee
        Employee testIndirectEmployee = new Employee();
        testIndirectEmployee.setFirstName("John");
        testIndirectEmployee.setLastName("Developer");
        testIndirectEmployee.setDepartment("Engineering");
        testIndirectEmployee.setPosition("Developer I");

        //Create indirect reporting employee using POST request to generate employeeId
        Employee createdIndirectEmployee = restTemplate.postForEntity(employeeUrl, testIndirectEmployee, Employee.class).getBody();

        //Initialize direct reporting employee
        Employee testDirectEmployee = new Employee();
        testIndirectEmployee.setFirstName("John");
        testDirectEmployee.setLastName("Engineer");
        testDirectEmployee.setDepartment("Engineering");
        testDirectEmployee.setPosition("Software Engineer");

        //Add indirect reporting employee
        List<Employee> reportingEmployees = new ArrayList<Employee>();
        reportingEmployees.add(createdIndirectEmployee);
        testDirectEmployee.setDirectReports(reportingEmployees);

        //Create direct reporting employee using POST request to generate employeeId
        Employee createdDirectEmployee = restTemplate.postForEntity(employeeUrl, testDirectEmployee, Employee.class).getBody();

        //Iniitalize manager employee
        Employee testManagerEmployee = new Employee();
        testManagerEmployee.setFirstName("John");
        testManagerEmployee.setLastName("Manager");
        testManagerEmployee.setDepartment("Engineering");
        testManagerEmployee.setPosition("Manager");

        //Add direct reporting employee
        reportingEmployees = new ArrayList<Employee>();
        reportingEmployees.add(createdDirectEmployee);
        testManagerEmployee.setDirectReports(reportingEmployees);

        //Create manager employee using POST request
        Employee createdManagerEmployee = restTemplate.postForEntity(employeeUrl, testManagerEmployee, Employee.class).getBody();

        ReportingStructure readManagerReportingStructure = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, createdManagerEmployee.getEmployeeId()).getBody();

        assertNotNull(readManagerReportingStructure);
        assertEmployeeEquivalence(testManagerEmployee, readManagerReportingStructure.getEmployee());
        assertEquals(2, readManagerReportingStructure.getNumberOfReports());
    }

    /**
     * Helper method for asserting employee equivalence
     * @param expected 
     * @param actual
     */
    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
