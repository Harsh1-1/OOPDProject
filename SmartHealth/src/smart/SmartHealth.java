package smart;
import java.util.Scanner;

class SmartHealth {
	static User curUser; //the current user
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in); //for testing
		State s = new Login(sc); //Starting state set to Login UI
		Testdb db = new Testdb();
		db.databaseconnect();
		while(true){
			//Handle the current state's work and transition to the next state.
			s = s.handle();
		}
	}

}
