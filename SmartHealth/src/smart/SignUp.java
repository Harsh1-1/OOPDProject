package smart;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * State for handling Sign Up UI and related for
 */
class SignUp extends State{
	
	private final static int END_USER = 1;
	private final static int MODERATOR = 2;
	private final static int ADMIN = 3;
	private final static int FIRST_NAME = 0;
	private final static int LAST_NAME = 1;
	private final static int PRIMARY_EMAIL = 2;
	private final static int SECONDARY_EMAIL = 3;
	private final static int PASSWORD = 4;
	private final static int USERID = 5;
	private final static int POSTAL_ADDRESS = 6;
	private final static int ABOUT_ME = 7;
	private final static int PROFILE_PIC1 = 8;
	private final static int PROFILE_PIC2 = 9;
	private final static int PROFILE_PIC3 = 10;
	//Options for signup
	private static final String options[] = {"First Name", "Last Name", 
			"Primary E-mail ID",
			"Secondary E-mail ID", "Password", "UserID",
			"Postal Address", "About Me", "3 Profile picture links"}; 
	//Users answers
	private String commonDetails[];
	
	State handle(){
		//Choices to be given to user
		System.out.println("Select user type ");
		System.out.println("1. End User");
		System.out.println("2. Moderator");
		System.out.println("3. Admin");
		int choice = sc.nextInt();
		
		System.out.println("Please enter the following details ");
		//Get details common to all and return in case of error
		if(!getCommonDetails()) return this;
		
		User user = null;
		switch(choice){
		case END_USER : //Create a normal user
			user = new EndUser(commonDetails[FIRST_NAME],commonDetails[LAST_NAME],commonDetails[PRIMARY_EMAIL],
					commonDetails[SECONDARY_EMAIL],commonDetails[PASSWORD],commonDetails[USERID],
					commonDetails[POSTAL_ADDRESS],commonDetails[ABOUT_ME],commonDetails[PROFILE_PIC1],
					commonDetails[PROFILE_PIC2], commonDetails[PROFILE_PIC3]);
			break;
		case 2 : //Take Moderator specific details
			System.out.println("Emergency Contact number");
			String emergencyContact = sc.next();
			System.out.println("Choose your qualifications separated by spaces"
					+ " and press 'N' to end : ");
			//Display the available qualifications
			for(int i=1;i<Global.acceptedQualifications.length;i++){
				System.out.println(i + ". " + Global.acceptedQualifications[i]);
			}
			//Maintain unique choices
			TreeSet<Integer> qualChoices = new TreeSet<Integer>();
			//Take input in specified format
			while(sc.hasNextInt()) qualChoices.add(sc.nextInt());
			sc.next();
			
			ArrayList<String> qualifications = new ArrayList<String>();
			for(int i : qualChoices){
				//Check and add qualifications and return in case of error
				if(i > 0 && i < Global.acceptedQualifications.length){
					qualifications.add(Global.acceptedQualifications[i]);
				}
				else{
					System.out.println("Invalid Qualification Choices");
					return this;
				}
			}
			//Create new moderator
			user = new Moderator(commonDetails[FIRST_NAME],commonDetails[LAST_NAME],commonDetails[PRIMARY_EMAIL],
					commonDetails[SECONDARY_EMAIL],commonDetails[PASSWORD],commonDetails[USERID],
					commonDetails[POSTAL_ADDRESS],commonDetails[ABOUT_ME],commonDetails[PROFILE_PIC1],
					commonDetails[PROFILE_PIC2], commonDetails[PROFILE_PIC3], 
					emergencyContact, qualifications);
			break;
		case 3 : //Take Admin specific details
			System.out.println("Emergency Contact number");
			emergencyContact = sc.next();
			//create new admin
			user = new Admin(commonDetails[FIRST_NAME],commonDetails[LAST_NAME],commonDetails[PRIMARY_EMAIL],
					commonDetails[SECONDARY_EMAIL],commonDetails[PASSWORD],commonDetails[USERID],
					commonDetails[POSTAL_ADDRESS],commonDetails[ABOUT_ME],commonDetails[PROFILE_PIC1],
					commonDetails[PROFILE_PIC2], commonDetails[PROFILE_PIC3], emergencyContact);
			break;
		default : System.out.println("Invalid Choice. Please Enter a valid Choice");
		}
		if(user != null){
			//If user not already present than store him in database
			if(store(user)){
				return new Login(sc); //return to login scrren
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
	
	/**
	 * This function gets details common to all
	 * @return Whether the details entered were correct or not
	 */
	private boolean getCommonDetails(){
		for(int i=0; i < options.length - 1; i++){
			System.out.println(options[i] + ": ");
			if(!options[i].equals("About Me")){
				commonDetails[i] = sc.next();
				//Check for userID's size
				if(options[i].equals("UserID")){
					if(commonDetails[i].length() > 20){
						System.out.println("UserID must be less than 20 characters");
						return false; //return an error has occured
					}
				}
			}
			else{ //Specific implementation for About Me
				sc.nextLine(); //skip previous new line
				commonDetails[i] = sc.nextLine();
			}
		}
		//get profile picture links
		System.out.println("Enter links of 3 Profile pics ");
		commonDetails[PROFILE_PIC1] = sc.next();
		commonDetails[PROFILE_PIC2] = sc.next();
		commonDetails[PROFILE_PIC3] = sc.next();
		return true; //executed without error
	}
	SignUp(Scanner sc){
		super(sc);
		commonDetails = new String[options.length + 3];
	}
}
