package smart;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Class for handling the work when the user has logged in.
 * Can make a transition to itself, Login and Update
 */
class LoggedIn extends State{

	private final static int DISPLAY = 1;
	private final static int UPDATE = 2;
	private final static int QUIT = 3;
	private final static int LOGOUT = 4;
	private final static int JOIN_AGAIN = 5;
	
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
		case DISPLAY : //display users profile info
			SmartHealth.curUser.displayProfileInfo();
			break;
		case UPDATE : //Transition to Update state
			return new Update(sc);
		case QUIT : //Set that user has quit
			SmartHealth.curUser.quit();
			quitUser(SmartHealth.curUser.getUserId());
			SmartHealth.curUser = null;
			return new Login(sc);
		case LOGOUT : //Logout the current user
			SmartHealth.curUser = null;
			return new Login(sc);
		case JOIN_AGAIN : //Re validate a user who had quit before
			SmartHealth.curUser.join();
			joinUser(SmartHealth.curUser.getUserId());
			break;
		default : System.out.println("Invalid choice. Please enter a valid choice.");
		}
		return this;
	}
	
	private void quitUser(String userID){
		String query = "UPDATE User SET Status = 0 WHERE UserName = '" + userID + "';";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement() )
			{
				s.executeUpdate(query);
			}
			catch(SQLException ex){
				System.out.println("Update failed");
				ex.getMessage();
			}
	}
	
	private void joinUser(String userID){
		String query = "UPDATE User SET Status = 1 WHERE UserName = '" + userID + "';";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement() )
			{
				s.executeUpdate(query);
			}
			catch(SQLException ex){
				System.out.println("Update failed");
				ex.getMessage();
			}
	}
	
	LoggedIn(Scanner sc){
		super(sc);
	}

}
