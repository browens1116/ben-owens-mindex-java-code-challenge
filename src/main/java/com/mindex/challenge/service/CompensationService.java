package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * Service interface for business logic related to creating and reading a Compensation
 * @author Ben Owens
 */
public interface CompensationService {
    /**
     * Create a Compensation
     * @param compensation to create
     * @return a newly created Compensation
     */
    Compensation create(Compensation compensation);
    
    /**
     * Read a Compensation
     * @param id of the requested employee
     * @return a previously created Compensation
     */
    Compensation read(String id);
}
