package smart;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Abhi
 *
 */
class LoggedIn extends State{

	State handle(){
		System.out.println("1. Update profile info");
		System.out.println("2. Quit Profile");
		System.out.println("Enter your choice ");
		int choice = sc.nextInt();
		switch(choice){
		case 1 : return new Update(sc);
		case 2 : break;
		default : System.out.println("Invalid choice. Please enter a valid choice.");
		}
		return this;
	}

	LoggedIn(Scanner sc){
		super(sc);
	}

}
