package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * REST Controller for reading a ReportingStructure
 * @author Ben Owens
 */
@RestController
public class ReportingStructureController {
    
    /**
     * The ReportingStructure Service
     */
    @Autowired
    private ReportingStructureService reportingStructureService;

    /**
     * Maps HTTP GET request to the create method of the ReportingStructureService
     * @param id of the requested employee, derived from the path variable
     * @return the ReportingStructure of the requested employee
     */
    @GetMapping("reporting_structure/{id}")
    public ReportingStructure create(@PathVariable String id) {
        return reportingStructureService.create(id);
    }
}
