package smart;
import java.util.Scanner;

class LoggedIn extends State{

	State handle(){
		int choice;
		if(!SmartHealth.curUser.hasQuit()){
			System.out.println("1. Display profile info");
			System.out.println("2. Update profile info");
			System.out.println("3. Quit Profile");
			System.out.println("4. Logout");
			System.out.println("Enter your choice ");
			choice = sc.nextInt();
		}
		else{
			System.out.println("1. Logout");
			System.out.println("2. Join Again");
			System.out.println("Enter your choice ");
			choice = sc.nextInt();
			choice += 3;
		}
		switch(choice){
		case 1 :
			SmartHealth.curUser.displayProfileInfo();
			break;
		case 2 : 
			return new Update(sc);
		case 3 : 
			SmartHealth.curUser.quit();
			SmartHealth.curUser = null;
			SmartHealth.curPos = -1;
			return new Login(sc);
		case 4 : 
			SmartHealth.curUser = null;
			SmartHealth.curPos = -1;
			return new Login(sc);
		case 5 :
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
