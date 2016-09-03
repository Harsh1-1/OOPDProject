package smart;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * State for Updating users details. Can transition to itself and Logged in
 */
class Update extends State{
	private static final int FIRST_NAME = 1;
	private static final int LAST_NAME = 2;
	private static final int SECONDARY_EMAIL = 3;
	private static final int PASSWORD = 4;
	private static final int POSTAL_ADDRESS = 5;
	private static final int ABOUT_ME = 6;
	private static final int PROFILE_PICS = 7;
	private static final int EMERGENCY_CONTACT = 8;
	private static final int QUALIFICATIONS = 9;
	//options to be given
	private static final String options[] = {"", "First Name", "Last Name", 
			"Secondary E-mail ID", "Password", "Postal Address", 
			"About Me", "Profile Picture Links", "Emergency Contact", "Qualifications"};
	
	State handle(){
		int temp;
		//Set available options based on usertype
		if(SmartHealth.curUser.getUserType().equals("MOD")){
			temp = options.length;
		}
		else if(SmartHealth.curUser.getUserType().equals("ADMIN")){
			temp = options.length - 1;
		}
		else{
			temp = options.length - 2;
		}
		//update is successful then transition to LoggedIn else remain in this state
		if(update(temp)){
			return new LoggedIn(sc);
		}
		return this;
	}
	
	/**
	 * Updates the user data
	 * @param l the number of options available
	 * @return Boolean indicating success or failure
	 */
	boolean update(int l){
		//get users choice
		System.out.println("Enter the detail to update");
		for(int i=1;i<l;i++){
			System.out.println(i + ". " + options[i]);
		}
		int choice = sc.nextInt();
		
		//check validity of choice
		if(choice > l || choice < 1){
			System.out.println("Invalid choice. Please enter a Valid choice.");
			return false;
		}
		//set s for some options
		String s="";
		if(!options[choice].equals("Profile Picture Links")) 
			System.out.println("Enter new " + options[choice] + " : ");
		if(!options[choice].equals("About Me") && 
		   !options[choice].equals("Profile Picture Links") && 
		   !options[choice].equals("Qualifications")) 
			s = sc.next();
		
		//update according to user's choice
		switch(choice){
		case FIRST_NAME : 
			SmartHealth.curUser.setFirstName(s);
			break;
		case LAST_NAME : 
			SmartHealth.curUser.setLastName(s);
			break;
		case SECONDARY_EMAIL : 
			SmartHealth.curUser.setSecondaryEmail(s);
			break;
		case PASSWORD : 
			SmartHealth.curUser.setPassword(s);
			break;
		case POSTAL_ADDRESS : 
			SmartHealth.curUser.setPostalAddress(s);
			break;
		case ABOUT_ME : //update about me
			sc.nextLine(); //remove previous new line
			s = sc.nextLine();
			SmartHealth.curUser.setAboutMe(s);
			break;
		case PROFILE_PICS : //update profile picture URL's
			String urls[] = SmartHealth.curUser.getPicURL();
			System.out.println("Enter the URL to change");
			for(int i=0;i<3;i++) System.out.println(i+1 + ". " + urls[i]);
			int ch = sc.nextInt();
			if(ch > 3 || ch < 1){
				System.out.println("Invalid choice. Enter a valid number");
				return false;
			}
			System.out.println("Enter new URL ");
			String modurl = sc.next();
			urls[ch - 1] = modurl;
			SmartHealth.curUser.setPicURL(urls);
			break;
		case EMERGENCY_CONTACT : //update emergency contact number for administrators and moderators
			if(SmartHealth.curUser.getUserType().equals("ADMIN")){
				Admin admin = (Admin)SmartHealth.curUser;
				admin.setEmergencyContact(s);
			}
			else if(SmartHealth.curUser.getUserType().equals("MOD")){
				Moderator moderator = (Moderator)SmartHealth.curUser;
				moderator.setEmergencyContact(s);
			}
			break;
		case QUALIFICATIONS : //Handle update of qualifications
			System.out.println("Choose your qualifications again separated by spaces"
					+ " and press 'N' to end : ");
			//Display available qualifications
			for(int i=1;i<Global.acceptedQualifications.length;i++){
				System.out.println(i + ". " + Global.acceptedQualifications[i]);
			}
			//Maintain unique qualifications
			TreeSet<Integer> qualChoices = new TreeSet<Integer>();
			//get user's choices
			while(sc.hasNextInt()) qualChoices.add(sc.nextInt());
			sc.next();
			ArrayList<String> qualifications = new ArrayList<String>();
			
			for(int i : qualChoices){
				//check validity of choices
				if(i > 0 && i < Global.acceptedQualifications.length){
					qualifications.add(Global.acceptedQualifications[i]);
				}
				else{
					System.out.println("Invalid Qualification Choices");
					return false;
				}
			}
			//update moderators details
			Moderator moderator = (Moderator)SmartHealth.curUser;
			moderator.setQualifications(qualifications);
			break;
		default : System.out.println("Invalid Choice. Please enter a valid choice");
			return false; //indicate error
		}
		return true; //indicate successful completion
	}
	
	Update(Scanner sc){
		super(sc);
	}

}
