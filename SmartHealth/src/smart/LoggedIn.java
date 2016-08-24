package smart;
import java.util.Scanner;

/**
 * Class for handling the work when the user has logged in.
 * Can make a transition to itself, Login and Update
 */
class LoggedIn extends State{

	State handle(){
		int choice;
		//Check whether the user has quit or not
		if(!SmartHealth.curUser.hasQuit()){
			//Get users's choice
			System.out.println("1. Display profile info");
			System.out.println("2. Update profile info");
			System.out.println("3. Quit Profile");
			System.out.println("4. Logout");
			System.out.println("Enter your choice ");
			choice = sc.nextInt();
		}
		else{
			//Get users choice if he quit
			System.out.println("1. Logout");
			System.out.println("2. Join Again");
			System.out.println("Enter your choice ");
			choice = sc.nextInt();
			choice += 3;
		}
		
		//Handle various cases
		switch(choice){
		case 1 : //display users profile info
			SmartHealth.curUser.displayProfileInfo();
			break;
		case 2 : //Transition to Update state
			return new Update(sc);
		case 3 : //Set that user has quit
			SmartHealth.curUser.quit();
			SmartHealth.curUser = null;
			return new Login(sc);
		case 4 : //Logout the current user
			SmartHealth.curUser = null;
			return new Login(sc);
		case 5 : //Re validate a user who had quit before
			SmartHealth.curUser.join();
			break;
		default : System.out.println("Invalid choice. Please enter a valid choice.");
		}
		return this;
	}
	
	LoggedIn(Scanner sc){
		super(sc);
	}

}
