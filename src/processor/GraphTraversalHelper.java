package processor;

import ui.UserInput;
import utils.Employee;
import utils.exceptions.RuntimeTimeoutException;
import utils.exceptions.TimeoutExitException;

import java.util.*;

public class GraphTraversalHelper {

    final Map<Integer, Set<Employee>> availabilityMap;
    final List<Employee> employeeList;
    final int numOfDays;
    public Node rootNode;
    public Node endNode;
    private long startTime;
    private long timeOutAfterMS = 20000;
    private boolean randomness;

    public GraphTraversalHelper(Map<Integer, Set<Employee>> availabilityMap, List<Employee> employeeList, int numOfDays, boolean randomness){
        this.availabilityMap =  availabilityMap;
        this.employeeList = employeeList;
        this.numOfDays = numOfDays;
        this.randomness = randomness;
    }


    /**
     * Call dfs search on a section of the schedule using this method
     */
    public boolean dfs(int from, int to) {
        Node root = new Node(this, from-1, null, null, randomness); //initialize a root node
        rootNode = root; //save root node in outer scope for reference in other methods
        startTime = System.currentTimeMillis();
        return doDFS(root, to);
    }

    /**
     * Called from within dfs(int from, int to), recursively searches for the first valid schedule in that range of days
     */
    public boolean doDFS(Node start, int to) {
    	
    	//if dfs is taking a very long time, prompt user to see if they would like to run again with random node exploration instead of
    	//ordered by who is missing the most shifts. if they want to run again, timeout this processor and catch the throw in UserInput which
    	//creates a new processor with random node ordering for each node's toExplore sorted set.
    	long endTime = System.currentTimeMillis();
    	if (endTime > (startTime + timeOutAfterMS)) {
    		if (randomness) { //if the new processor with randomness times out, just exit the dfs to try again with longer runtime and/or edited inputs
    			return false;
    		}

    		if (UserInput.timeOutPrompt()) {
                throw new TimeoutExitException();
    		}
        	throw new RuntimeTimeoutException(timeOutAfterMS); //comment out if debugging
    	}

        //if the node that was passed into the last recursive call of doDFS is valid & the day is equal to the target day, that represents a
        //successful schedule, so return true and save the root and valid leaf for use later if we need to find a new valid sub-schedule in this range
        if (start.day == to && start.valid) {
            endNode = start; //save the valid leaf node in outer scope for reference in other methods
            return true;
        }

        //shuffle and iterate through the set of Employees in the start Node's toExplore list
        for(NodeExplorationWrapper node: start.toExplore){
            Employee nextEmployee = node.employee;

            if (!start.explored.contains(nextEmployee)) {
                Node newNode = new Node(this, start.day + 1, start, nextEmployee, randomness); //create a new node, which removes the Node's Employee from start.toExplore

                if (newNode.valid) { //if this new node is valid, doDFS on it
                    boolean result = doDFS(newNode, to);
                    if ( result ){
                        return result;
                    }
                }
            }
        }
        return false;
    }
}
