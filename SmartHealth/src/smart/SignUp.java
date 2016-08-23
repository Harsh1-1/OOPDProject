package smart;
import java.util.ArrayList;
import java.util.Scanner;

class SignUp extends State{
	private static final String options[] = {"First Name", "Last Name", 
			"Primary E-mail ID",
			"Secondary E-mail ID", "Password", "UserID",
			"Postal Address", "About Me", "3 Profile picture links"};
	private String commonDetails[];
	
	State handle(){
		System.out.println("Select user type ");
		System.out.println("1. End User");
		System.out.println("2. Moderator");
		System.out.println("3. Admin");
		int choice = sc.nextInt();
		
		System.out.println("Please enter the following details ");
		getCommonDetails();
		User user = null;
		switch(choice){
		case 1 :
			user = new EndUser(commonDetails[0],commonDetails[1],commonDetails[2],
					commonDetails[3],commonDetails[4],commonDetails[5],
					commonDetails[6],commonDetails[7],commonDetails[8],
					commonDetails[9], commonDetails[10]); 
		case 2 :
			System.out.println("Emergency Contact number");
			String emergencyContact = sc.next();
			System.out.println("Choose your qualification : ");
			for(int i=1;i<Global.acceptedQualifications.length;i++){
				System.out.println(i + ". " + Global.acceptedQualifications[i]);
			}
			int qualchoice = sc.nextInt();
			ArrayList<String> qualifications = new ArrayList<String>();
			qualifications.add(Global.acceptedQualifications[qualchoice]);
			user = new Moderator(commonDetails[0],commonDetails[1],commonDetails[2], 
					commonDetails[3],commonDetails[4],commonDetails[5], 
					commonDetails[6],commonDetails[7], commonDetails[8],
					commonDetails[9], commonDetails[10], 
					emergencyContact, qualifications);
		case 3 :
			System.out.println("Emergency Contact number");
			emergencyContact = sc.next();
			user = new Admin(commonDetails[0],commonDetails[1],commonDetails[2],
					commonDetails[3],commonDetails[4],commonDetails[5],
					commonDetails[6],commonDetails[7],commonDetails[8],
					commonDetails[9], commonDetails[10], emergencyContact);
		default : System.out.println("Invalid Choice. Please Enter a valid Choice");
		}
		if(user != null){
			if(store(user)){
				return new Login(sc);
			}
		}
		return this;
	}

	private boolean store(User user){
		for(User u : Global.users){
			if(u.getUserId().equals(user.getUserId())){
				System.out.println("User Id already taken");
				return false;
			}
			else if(u.getPrimaryEmail().equals(user.getPrimaryEmail())){
				System.out.println("Primary EmailID already used");
				return false;
			}
		}
		Global.users.add(user);
		return true;
	}
	
	private void getCommonDetails(){
		for(int i=0; i < options.length; i++){
			System.out.println(options[i] + ": ");
			if(!options[i].equals("About me")) commonDetails[i] = sc.next();
			else{
				commonDetails[i] = sc.nextLine();
				//sc.next();
			}
		}
		System.out.println("Enter links of 3 Profile pics ");
		for(int i=0;i<3;i++)
		{
			commonDetails[options.length + i] = sc.next();
		}
	}
	SignUp(Scanner sc){
		super(sc);
		commonDetails = new String[options.length + 2];
	}
}
