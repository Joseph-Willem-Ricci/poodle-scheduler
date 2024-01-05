package com.poodle.backend;

import com.poodle.utils.*;
import java.util.List;


public class ScheduleRequest {
    private List<Employee> employeeList;
    private int numberOfDays;

    public ScheduleRequest() {}  // For Jackson..?

    public ScheduleRequest(List<Employee> employeeList, int numberOfDays) {
        this.employeeList = employeeList;
        this.numberOfDays = numberOfDays;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
