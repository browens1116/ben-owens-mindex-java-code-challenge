package com.mindex.challenge.data;

/**
 * Data Class representing a ReportingStructure
 * @author Ben Owens
 */
public class Compensation {
    /**
     * The employee
     */
    private Employee employee;

    /**
     * The employee's salary
     */
    private double salary;

    /**
     * The employee's effective date
     */
    private String effectiveDate;

    /**
     * Constructor
     */
    public Compensation() {

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
     * Getter for salary field
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Setter for the salary field
     * @param salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Getter for effective date field
     * @return the salary
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Setter for the effectiveDate field
     * @param effectiveDate to set
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
