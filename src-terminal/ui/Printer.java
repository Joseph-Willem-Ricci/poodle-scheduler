package ui;

import utils.Employee;
import utils.Schedule;

public class Printer {

	public Printer() {}
	
	public void print(Employee e) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Name: "        + e.getName()       +
					       ", Quota: "       + e.getQuota()      +
				           ", # of Shifts: " + e.getShiftCount() + "\n" +
				             "Schedule: "    + e.getSchedule()   + "\n");
		
		System.out.println(stringBuilder.toString());
	}
	
	public void print(Schedule schedule) {
		System.out.println(schedule);
	}

	public void print(String message) {
		System.out.println(message);
	}
	
}
