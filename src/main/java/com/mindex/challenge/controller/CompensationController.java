package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * REST Controller for creating & reading a Compensation
 * @author Ben Owens
 */
@RestController
public class CompensationController {

    /**
     * The Compensation Service
     */
    @Autowired 
    private CompensationService compensationService;

    /**
     * Maps HTTP POST request to the create method of the CompensationService
     * @param compensation to create, derived from the request body
     * @return a newly created Compensation
     */
    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        return compensationService.create(compensation);
    }

    /**
     * Maps HTTP GET request to the read method of the CompensationService
     * @param id of the requested employee, derived from the path variable
     * @return the Compensation of the requested employee
     */
    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        return compensationService.read(id);
    }
}
