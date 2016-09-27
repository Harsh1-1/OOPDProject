package smart;
import java.util.Scanner;

/**
 * Class for handling the work when the user has logged in.
 * Can make a transition to itself, Login and Update
 */
class LoggedIn extends State{

	private final static int DISPLAY = 1;
	private final static int UPDATE = 2;
	private final static int QUIT = 3;
	private final static int FRIENDS = 4;
	private final static int LOGOUT = 5;
	private final static int JOIN_AGAIN = 6;
	
	private models.LoggedIn model = new models.LoggedIn();
	
	State handle(){
		int choice;
		//Check whether the user has quit or not
		if(!SmartHealth.curUser.hasQuit()){
			//Get users's choice
			System.out.println("1. Display profile info");
			System.out.println("2. Update profile info");
			System.out.println("3. Quit Profile");
			if(!SmartHealth.curUser.getUserType().equals("MOD") && 
					!SmartHealth.curUser.getUserType().equals("ADMIN")){
				System.out.println("4. Friends");
				System.out.println("5. Logout");
			}else{
				System.out.println("4. Logout");
			}
			System.out.println("Enter your choice ");
			choice = sc.nextInt();
			if((SmartHealth.curUser.getUserType().equals("MOD") || 
					SmartHealth.curUser.getUserType().equals("ADMIN"))){
				if(choice < 1 || choice > 4){
					System.out.println("Invalid choice");
					return this;
				}
				if(choice == 4) choice = 5;
			}else if(choice < 1 || choice > 5){
				System.out.println("Invalid choice");
				return this;
			}
		}
		else{
			//Get users choice if he quit
			System.out.println("1. Logout");
			System.out.println("2. Join Again");
			System.out.println("Enter your choice ");
			choice = sc.nextInt();
			if(choice < 1 || choice > 2){
				System.out.println("Invalid choice");
				return this;
			}
			choice += 4;
		}
		//Handle various cases
		switch(choice){
		case DISPLAY : //display users profile info
			SmartHealth.curUser.displayProfileInfo();
			break;
		case UPDATE : //Transition to Update state
			return new Update(sc);
		case QUIT : //Set that user has quit
			SmartHealth.curUser.quit();
			model.quitUser(SmartHealth.curUser.getUserId());
			SmartHealth.curUser = null;
			return new Login(sc);
		case LOGOUT : //Logout the current user
			SmartHealth.curUser = null;
			return new Login(sc);
		case JOIN_AGAIN : //Re validate a user who had quit before
			SmartHealth.curUser.join();
			model.joinUser(SmartHealth.curUser.getUserId());
			break;
		case FRIENDS:
			return new Friends(sc);
		default : System.out.println("Invalid choice. Please enter a valid choice.");
		}
		return this;
	}
	
	LoggedIn(Scanner sc){
		super(sc);
	}

}
