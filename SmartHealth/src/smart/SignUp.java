package smart;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import beans.Address;
import beans.Admin;
import beans.EndUser;
import beans.Moderator;
import beans.Qualification;
import beans.User;

/**
 * State for handling Sign Up UI and related for
 */
class SignUp extends State implements UserForm{
	
	private final static int END_USER = 1;
	private final static int MODERATOR = 2;
	private final static int ADMIN = 3;
	private final static int FIRST_NAME = 0;
	private final static int LAST_NAME = 1;
	private final static int PRIMARY_EMAIL = 2;
	private final static int SECONDARY_EMAIL = 3;
	private final static int PASSWORD = 4;
	private final static int USERID = 5;
	private final static int STREET_NUMBER = 6;
	private final static int STREET_NAME = 7;
	private final static int MAJOR_MUNICIPALITY = 8;
	private final static int GOVERNING_DISTRICT = 9;
	private final static int POSTAL_AREA = 10;
	private final static int ABOUT_ME = 11;
	private final static int PROFILE_PIC1 = 12;
	private final static int PROFILE_PIC2 = 13;
	private final static int PROFILE_PIC3 = 14;
	
	private models.SignUp model = new models.SignUp();
	//Options for signup
	private static final String options[] = {"First Name", "Last Name", 
			"Primary E-mail ID",
			"Secondary E-mail ID", "Password", "UserID",
			"Street Number", "Street Name", "Major Municipality", 
			"Governing District", "Postal Area",
			"About Me", "3 Profile picture links"}; 
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
					new Address(commonDetails[STREET_NUMBER], commonDetails[STREET_NAME],
							commonDetails[MAJOR_MUNICIPALITY], commonDetails[GOVERNING_DISTRICT], 
							commonDetails[POSTAL_AREA]), 
					commonDetails[ABOUT_ME],commonDetails[PROFILE_PIC1],
					commonDetails[PROFILE_PIC2], commonDetails[PROFILE_PIC3],false);
			break;
		case MODERATOR : //Take Moderator specific details
			System.out.println("Emergency Contact number");
			String emergencyContact = sc.next();
			if(!isValidContactNumber(emergencyContact)){
				System.out.println("Invalid contact number");
				return this;
			}
			System.out.println("Choose your qualifications separated by spaces"
					+ " and press 'N' to end : ");
			//Display the available qualifications
			ArrayList<Qualification> acceptedQualifications = model.getQualifications();
			for(int i=0;i<acceptedQualifications.size();i++){
				System.out.println(i+1 + ". " + acceptedQualifications.get(i));
			}
			//Maintain unique choices
			TreeSet<Integer> qualChoices = new TreeSet<Integer>();
			//Take input in specified format
			while(sc.hasNextInt()) qualChoices.add(sc.nextInt());
			sc.next();
			
			ArrayList<Qualification> qualifications = new ArrayList<Qualification>();
			for(int i : qualChoices){
				//Check and add qualifications and return in case of error
				if(i > 0 && i <= acceptedQualifications.size()){
					qualifications.add(acceptedQualifications.get(i-1));
				}
				else{
					System.out.println("Invalid Qualification Choices");
					return this;
				}
			}
			//Create new moderator
			user = new Moderator(commonDetails[FIRST_NAME],commonDetails[LAST_NAME],commonDetails[PRIMARY_EMAIL],
					commonDetails[SECONDARY_EMAIL],commonDetails[PASSWORD],commonDetails[USERID],
					new Address(commonDetails[STREET_NUMBER], commonDetails[STREET_NAME],
							commonDetails[MAJOR_MUNICIPALITY], commonDetails[GOVERNING_DISTRICT], 
							commonDetails[POSTAL_AREA]), 
					commonDetails[ABOUT_ME],commonDetails[PROFILE_PIC1],
					commonDetails[PROFILE_PIC2], commonDetails[PROFILE_PIC3], false, 
					emergencyContact, qualifications);
			break;
		case ADMIN : //Take administrator specific details
			System.out.println("Emergency Contact number");
			emergencyContact = sc.next();
			if(!isValidContactNumber(emergencyContact)){
				System.out.println("Invalid contact number");
				return this;
			}
			//create new administrator
			user = new Admin(commonDetails[FIRST_NAME],commonDetails[LAST_NAME],commonDetails[PRIMARY_EMAIL],
					commonDetails[SECONDARY_EMAIL],commonDetails[PASSWORD],commonDetails[USERID],
					new Address(commonDetails[STREET_NUMBER], commonDetails[STREET_NAME],
							commonDetails[MAJOR_MUNICIPALITY], commonDetails[GOVERNING_DISTRICT], 
							commonDetails[POSTAL_AREA]), 
					commonDetails[ABOUT_ME],commonDetails[PROFILE_PIC1],
					commonDetails[PROFILE_PIC2], commonDetails[PROFILE_PIC3], false, 
					emergencyContact);
			break;
		default : System.out.println("Invalid Choice. Please Enter a valid Choice");
		}
		if(user != null){
			//If user not already present than store him in database
			if(model.store(user)){
				return new Login(sc); //return to login screen
			}
		}
		return this;
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
						System.out.println("User Name must be less than 20 characters");
						return false;
					}
				}
				else if(options[i].equals("Primary E-mail ID")){
					if(!isValidEmail(commonDetails[PRIMARY_EMAIL])){
						System.out.println("Email ID is invalid");
						return false;
					}
				}
				else if(options[i].equals("Secondary E-mail ID")){
					if(!isValidEmail(commonDetails[SECONDARY_EMAIL])){
						System.out.println("Email ID is invalid");
						return false;
					}
					if(commonDetails[SECONDARY_EMAIL].equalsIgnoreCase(commonDetails[PRIMARY_EMAIL])){
						System.out.println("Primary and Secondary Email cannot be same");
						return false;
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
		if(!isValidURL(commonDetails[PROFILE_PIC1])){
			System.out.println("Invalid URL");
			return false;
		}
		commonDetails[PROFILE_PIC2] = sc.next();
		if(!isValidURL(commonDetails[PROFILE_PIC2])){
			System.out.println("Invalid URL");
			return false;
		}
		commonDetails[PROFILE_PIC3] = sc.next();
		if(!isValidURL(commonDetails[PROFILE_PIC3])){
			System.out.println("Invalid URL");
			return false;
		}
		return true; //executed without error
	}
	
	SignUp(Scanner sc){
		super(sc);
		commonDetails = new String[options.length + 3];
	}
		
}
