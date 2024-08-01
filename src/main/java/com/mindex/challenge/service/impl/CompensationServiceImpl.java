package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class implementation of the CompensationService interface.
 * Contains business logic related to creating and reading a Compensation
 * @author Ben Owens
 */
@Service
public class CompensationServiceImpl implements CompensationService{
    
    /**
     * Repository of Compensation Data
     */
    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * Repository of Employee Data
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Compensation create(Compensation compensation) {
        //Insert compensation into repository
        compensationRepository.insert(compensation);
        return compensation;
    }

    @Override
    public Compensation read(String id) {
        //Find employee via given id
        Employee employee = employeeRepository.findByEmployeeId(id);

        //Throw exception if employeeId is invalid
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        //Find compensation via the recently found employee
        Compensation compensation = compensationRepository.findByEmployee(employee);

        //Throw exception if compensation has not been created for the given employee
        if (compensation == null) {
            throw new RuntimeException("Could not find compensation for employeeId: " + id);
        }


        return compensation;
    }
}
