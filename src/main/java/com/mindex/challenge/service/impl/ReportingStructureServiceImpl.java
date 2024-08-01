package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class implementation of the ReportingStructureService interface.
 * Contains business logic related to creating a ReportingStructure
 * @author Ben Owens
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    
    /**
     * Repository of Employee Data
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure create(String id) {
        
        //Find the employee via the given id
        Employee employee = employeeRepository.findByEmployeeId(id);

        //Throw exception if the employeeId is invalid
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        //Create the new ReportingStructure and set the Employee field
        ReportingStructure reportingStructure = new ReportingStructure(); 
        reportingStructure.setEmployee(employee);

        //Find the number of reporting employees
        int numberOfReports = traverseReportingEmployees(employee); 

        //Set the numberOfReports field
        reportingStructure.setNumberOfReports(numberOfReports);

        return reportingStructure;
    }

    /**
     * Get number of reporting employees using in-order traversal
     * @param employee to retrieve number of reports
     * @return number of reporting employees
     */
    int traverseReportingEmployees(Employee employee)
    {
        //If there are no direct reports, return 0
        if (employee.getDirectReports() == null) {
            return 0;
        }

        //Begin by setting the number of reports to the number of direct reports
        int numberOfReports = employee.getDirectReports().size();

        //In order to retreive the direct reports of lower level employees, we must retreive the employees from the EmployeeRepository
        
        //Convert list of direct reports to an array
        Employee[] reportingEmployees = employee.getDirectReports().toArray(new Employee[0]);

        //Loop over array of direct reports
        for (int i = 0; i < reportingEmployees.length; i++) {
            //Retreive reporting employee from the EmployeeRepository
            Employee reportingEmployee = employeeRepository.findByEmployeeId(reportingEmployees[i].getEmployeeId());

            //Recursively add the direct reports of the reporting employee
            numberOfReports += traverseReportingEmployees(reportingEmployee);
        }

        return numberOfReports;
    }
}
