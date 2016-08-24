package smart;
import java.util.Scanner;

/**
 * Login class deals with the Login UI and its associated work.
 * Can make a transition to itself, LoggedIn and SignUp
 */
class Login extends State{
	State handle(){
		//Print available choices
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("Enter your choice ");
		int choice = sc.nextInt(); //get users choice
		
		//Choice specific actions
		switch(choice){
		case 1 :
			System.out.println("Enter your Primary Email ID : ");
			String emailID = sc.next();
			System.out.println("Enter your Password : ");
			String password = sc.next();
			
			//Check if the user is valid user or not
			SmartHealth.curUser = validUser(emailID, password);
			if(SmartHealth.curUser != null){
				return new LoggedIn(sc); //if a valid user Log him in
			}
			//Print error message and continue with this state only
			else System.out.println("Invalid UserID or Password");
			break;
		
		case 2 : return new SignUp(sc); //transition to SignUp state
		
		//Print error message and continue with this state only
		default : System.out.println("Invlaid Choice. Please Enter a valid choice.");
			break;
		}
		return this; //remain in the current state. Needs to be updated for GUI
	}
	
	/**
	 * returns the user if valid user else null
	 * @param id The users primary mail ID
	 * @param password The users password
	 * @return The user if present in database else null
	 */
	private User validUser(String id, String password){
		for(int i=0;i<Global.users.size();i++){
			
			//if primary id is present then check for password
			if(Global.users.get(i).getPrimaryEmail().equals(id)){
				if(Global.users.get(i).getPassword().equals(password)){
					return Global.users.get(i); //return the valid user
				}
				else{
					//password did not match
					System.out.println("Incorrect Password");
					break;
				}
			}
		}
		System.out.println("Primary email Id not registered");
		return null; //Indicate error in finding the user
	}
	
	Login(Scanner sc){
		super(sc);
	}
}
