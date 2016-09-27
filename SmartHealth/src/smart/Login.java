package smart;
import java.util.Scanner;

/**
 * Login class deals with the Login UI and its associated work.
 * Can make a transition to itself, LoggedIn and SignUp
 */
class Login extends State{
	private views.Login view = new views.Login();
	private models.Login model = new models.Login();
	
	State handle(){
		int choice = view.getchoice(); //get users choice
		
		//Choice specific actions
		switch(choice){
		case 1 :
			String emailID = view.getPrimaryEmailID();
			String password = view.getPassword();
			
			//Check if the user is valid user or not
			SmartHealth.curUser = model.validUser(emailID, password);
			if(SmartHealth.curUser != null){
				return new LoggedIn(sc); //if a valid user Log him in
			}
			//Print error message and continue with this state only
			else view.setError("Invalid UserID or Password");
			break;
		
		case 2 : return new SignUp(sc); //transition to SignUp state
		
		//Print error message and continue with this state only
		default : view.setError("Invlaid Choice. Please Enter a valid choice.");
			break;
		}
		return this; //remain in the current state. Needs to be updated for GUI
	}
	
	Login(Scanner sc){
		super(sc);
	}
}
