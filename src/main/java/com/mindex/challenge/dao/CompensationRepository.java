package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface used for storing and retreiving Compensation data
 * @author Ben Owens
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    /**
     * Finds Compensation data given an Employee
     * @param employee to find Compensation for
     * @return Compensation
     */
    Compensation findByEmployee(Employee employee);
}
