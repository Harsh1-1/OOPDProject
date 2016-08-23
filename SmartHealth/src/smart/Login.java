package smart;
import java.util.Scanner;

class Login extends State{
	State handle(){
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("3. Enter your choice ");
		int choice = sc.nextInt();
		switch(choice){
		case 1 :
			System.out.println("Enter your ID : ");
			//Email id = new Email(sc.next());
			String emailID = sc.next();
			System.out.println("Enter your Password : ");
			String password = sc.next();
			SmartHealth.curUser = validUser(emailID, password);
			if(SmartHealth.curUser != null){
				return new LoggedIn(sc);
			}
			else System.out.println("Invalid UserID or Password");
			break;
		case 2 : return new SignUp(sc);
		default : System.out.println("Invlaid Choice. Please Enter a valid choice.");
			break;
		}
		return this;
	}
	
	private User validUser(String id, String password){
		for(int i=0;i<Global.users.size();i++){
			if(Global.users.get(i).getPrimaryEmail().equals(id)){
				if(Global.users.get(i).getPassword().equals(password)){
					SmartHealth.curPos = i;
					return Global.users.get(i);
				}
				else{
					System.out.println("Incorrect Password");
					break;
				}
			}
		}
		System.out.println("Primary email Id not registered");
		return null;
	}
	
	Login(Scanner sc){
		super(sc);
	}
}
