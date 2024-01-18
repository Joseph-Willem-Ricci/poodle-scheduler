package com.poodle.backend;
import java.util.SortedSet;
import java.util.Map;

public class ScheduleResponse {
    private final Map<Integer, String> schedule;
    private final Map<String, SortedSet<Integer>> employeeShifts;

    public ScheduleResponse(Map<Integer, String> schedule, Map<String, SortedSet<Integer>> employeeShifts) {
        this.schedule = schedule;
        this.employeeShifts = employeeShifts;
    }

    public Map<Integer, String> getSchedule() {
        return schedule;
    }

    public Map<String, SortedSet<Integer>> getEmployeeShifts() {
        return employeeShifts;
    }
}
