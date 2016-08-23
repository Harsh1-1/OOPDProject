package smart;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Abhi
 *
 */
abstract class State {
	protected Scanner sc;
	abstract State handle();
	State(Scanner sc){
		this.sc = sc;
	}
}
