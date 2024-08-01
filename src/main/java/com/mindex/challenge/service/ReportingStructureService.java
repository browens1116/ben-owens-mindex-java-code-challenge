package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

/**
 * Service interface for business logic related to creating a ReportingStructure
 * @author Ben Owens
 */
public interface ReportingStructureService {
    /**
     * Create a ReportingStructure
     * @param id of the requested Employee
     * @return a dynamically created ReportingStructure
     */
    ReportingStructure create(String id);
}
