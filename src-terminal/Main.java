import java.util.*;

import datamanagement.TextFileParser;
import ui.CommandLineUserInterface;
import utils.Employee;

public class Main {
	
	
	
    public static void main(String[] args) {

    	if (args.length != 0 && args[0].equals("demo")) { //if runtime arg is "demo", run the demo
            runDemo();
            return;
        }

        String fileName = "input.txt";
        if (args.length >= 1) { //if a runtime arg is supplied but not equal to "demo", read that file
    		fileName = args[0];
    	}
        run(fileName);
        return;
    }

    public static void runDemo(){
        Demo demo = new Demo();
        demo.run();
        return;
    }

    public static void run(String fileName){
        CommandLineUserInterface ui = new CommandLineUserInterface();
        try{

            TextFileParser parser = new TextFileParser(fileName);
            List<Employee> employeeList = parser.getEmployees();
            int days = parser.getNumberOfDays();
            ui.run(employeeList, days, false);

        } catch(Exception ex){
            ui.printMessage(ex.getMessage());
        }
    }
    
}
