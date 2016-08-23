package smart;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Abhi
 *
 */
class SignUp extends State{
	private static final String options[] = {"First Name", "Last Name", 
			"Primary E-mail ID",
			"Secondary E-mail ID", "Password", "UserID",
			"Postal Address", "About Me", "3 Profile picture links"};
	private String details[];
	
	State handle(){
		System.out.println("Select user type ");
		System.out.println("1. End User");
		System.out.println("2. Moderator");
		System.out.println("3. Admin");
		int choice = sc.nextInt();
		
		System.out.println("Please enter the following details ");
		getCommonDetails();
		
		switch(choice){
		case 1 :
			EndUser user = new EndUser(details[0],details[1],details[2],details[3],
					details[4],details[5],details[6],details[7],
					details[8],details[9], details[10]); 
			return new Login(sc);
		case 2 :
			System.out.println("Emergency Contact number");
			String emergencyContact = sc.next();
			System.out.println("Enter all the applicable Qualifications separated by spaces ");
			Admin admin = new Admin(details[0],details[1],details[2],details[3],
					details[4],details[5],details[6],details[7],
					details[8],details[9], details[10], emergencyContact);
			return new Login(sc);
		case 3 :
			System.out.println("Emergency Contact number");
			emergencyContact = sc.next();
			Moderator moderator = new Moderator(details[0],details[1],details[2],details[3],
					details[4],details[5],details[6],details[7],
					details[8],details[9], details[10], emergencyContact);
			return new Login(sc);
		default : System.out.println("Invalid Choice. Please Enter a valid Choice");
		}
		return this;
	}

	private void getCommonDetails(){
		for(int i=0; i < options.length - 1; i++){
			System.out.println(options[i]);
			if(!options[i].equals("About me")) details[i] = sc.next();
			else{
				details[i] = sc.nextLine();
				//sc.next();
			}
		}
		System.out.println("Enter links of 3 Profile pics ");
		for(int i=0;i<3;i++)
		{
			details[options.length + i] = sc.next();
		}
	}
	SignUp(Scanner sc){
		super(sc);
		details = new String[options.length + 2];
	}
}
