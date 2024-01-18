package com.poodle.utils;

import java.util.*;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private int quota;
    private SortedSet<Integer> availability;
    private SortedSet<Integer> schedule = new TreeSet<Integer>();

    public Employee() {} // for Jackson deserialization..?

    public Employee(String name, int quota, int[] availability){
        this.name = name;
        this.quota = quota;
        this.availability = Arrays.stream(availability)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Employee(String name, int quota, Collection<Integer> availability){
        this.name = name;
        this.quota = quota;
        this.availability = new TreeSet<Integer>(availability);
    }

    public boolean assignDay(int day){
        if (schedule.contains(day)){
            return true;
        }
        if (
            !availability.contains(day) ||
            schedule.size() >= quota ||
            schedule.contains(day-1) ||
            schedule.contains(day+1)
        ){
            return false;
        }
        schedule.add(day);
        return true;
    }


    /**
     * Getters
     */
    public String getName() {
        return this.name;
    }
    public int getQuota() {
    	return this.quota;
    }

    public SortedSet<Integer> getAvailability() {
        return this.availability;
    }

    public int getShiftCount() {
    	return this.schedule.size();
    }

    public SortedSet<Integer> getSchedule() {
    	return this.schedule;
    }

    /**
     * Setters
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public void setAvailability(SortedSet<Integer> availability) {
        this.availability = availability;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", quota=" + quota +
                ", schedule=" + schedule +
                ", availability=" + availability +
                '}';
    }
    
}