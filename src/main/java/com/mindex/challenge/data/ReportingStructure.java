package com.mindex.challenge.data;

/**
 * Data Class representing a ReportingStructure
 * @author Ben Owens
 */
public class ReportingStructure {
    /**
     * The employee
     */
    private Employee employee;

    /**
     * The total number of reports for the given employee.
     * Includes direct reports and all distinct reports
     */
    private int numberOfReports;

    /**
     * Constructor
     */
    public ReportingStructure() {

    }

    /**
     * Getter for employee field
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Setter for the employee field
     * @param employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Getter for the number of reports
     * @return the number of reports
     */
    public int getNumberOfReports() {
        return numberOfReports;
    }

    /**
     * Setter for the number of reports
     * @param numberOfReports to set
     */
    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
