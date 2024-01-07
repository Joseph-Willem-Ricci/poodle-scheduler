package com.poodle.utils;

import java.util.*;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private int quota;
    private SortedSet<Integer> dates;
    private SortedSet<Integer> schedule = new TreeSet<Integer>();

    public Employee() {} // for Jackson deserialization..?

    public Employee(String name, int quota, int[] dates){
        this.name = name;
        this.quota = quota;
        this.dates = Arrays.stream(dates)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Employee(String name, int quota, Collection<Integer> dates){
        this.name = name;
        this.quota = quota;
        this.dates = new TreeSet<Integer>(dates);
    }

    public SortedSet<Integer> getSchedule(){
        return this.schedule;
    }

    public boolean assignDay(int day){
        if (schedule.contains(day)){
            return true;
        }
        if (
            !dates.contains(day) ||
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

    public SortedSet<Integer> getDates() {
        return this.dates;
    }

    public int getShiftCount() {
    	return this.schedule.size();
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

    public void setDates(SortedSet<Integer> dates) {
        this.dates = dates;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", quota=" + quota +
                ", schedule=" + schedule +
                ", availability=" + dates +
                '}';
    }
    
}