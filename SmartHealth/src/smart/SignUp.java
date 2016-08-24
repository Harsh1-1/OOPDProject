package smart;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

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
		if(!getCommonDetails()) return this;
		User user = null;
		switch(choice){
		case 1 :
			user = new EndUser(commonDetails[0],commonDetails[1],commonDetails[2],
					commonDetails[3],commonDetails[4],commonDetails[5],
					commonDetails[6],commonDetails[7],commonDetails[8],
					commonDetails[9], commonDetails[10]);
			break;
		case 2 :
			System.out.println("Emergency Contact number");
			String emergencyContact = sc.next();
			System.out.println("Choose your qualifications separated by spaces : ");
			for(int i=1;i<Global.acceptedQualifications.length;i++){
				System.out.println(i + ". " + Global.acceptedQualifications[i]);
			}
			TreeSet<Integer> qualChoices = new TreeSet<Integer>();
			while(sc.hasNextInt()) qualChoices.add(sc.nextInt());
			ArrayList<String> qualifications = new ArrayList<String>();
			for(int i : qualChoices){
				if(i > 0 && i < Global.acceptedQualifications.length){
					qualifications.add(Global.acceptedQualifications[i]);
				}
				else{
					System.out.println("Invalid Qualification Choices");
					return this;
				}
			}
			user = new Moderator(commonDetails[0],commonDetails[1],commonDetails[2], 
					commonDetails[3],commonDetails[4],commonDetails[5], 
					commonDetails[6],commonDetails[7], commonDetails[8],
					commonDetails[9], commonDetails[10], 
					emergencyContact, qualifications);
			break;
		case 3 :
			System.out.println("Emergency Contact number");
			emergencyContact = sc.next();
			user = new Admin(commonDetails[0],commonDetails[1],commonDetails[2],
					commonDetails[3],commonDetails[4],commonDetails[5],
					commonDetails[6],commonDetails[7],commonDetails[8],
					commonDetails[9], commonDetails[10], emergencyContact);
			break;
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
	
	private boolean getCommonDetails(){
		for(int i=0; i < options.length - 1; i++){
			System.out.println(options[i] + ": ");
			if(!options[i].equals("About Me")){
				commonDetails[i] = sc.next();
				if(options[i].equals("UserID")){
					if(commonDetails[i].length() > 20){
						System.out.println("UserID must be less than 20 characters");
						return false;
					}
				}
			}
			else{
				sc.nextLine();
				commonDetails[i] = sc.nextLine();
			}
		}
		System.out.println("Enter links of 3 Profile pics ");
		for(int i=0;i<3;i++){
			commonDetails[options.length + i - 1] = sc.next();
		}
		return true;
	}
	SignUp(Scanner sc){
		super(sc);
		commonDetails = new String[options.length + 3];
	}
}
