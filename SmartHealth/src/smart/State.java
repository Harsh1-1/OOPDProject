package smart;
import java.util.Scanner;

/**
 * State class defines the methods for defining the state 
 * in which the UI should be present.
 */
abstract class State {
	protected Scanner sc;
	
	/**
	 * The handle method acts as the main method of state and does all the necessary
	 * work and then returns the next state to transition to.
	 * @return State object which indicates the state to transition to.
	 */
	abstract State handle();
	State(Scanner sc){
		this.sc = sc;
	}
	
	State()
	{
		
	}
}
