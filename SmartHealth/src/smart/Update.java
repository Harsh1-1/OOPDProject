package smart;
import java.util.Scanner;

class Update extends State{
	private static final String options[] = {"First Name", "Last Name", 
			"Secondary E-mail ID", "Password", "Postal Address", 
			"About Me", "3 Profile picture links", "Emergency Contact"};
	
	State handle(){
		int temp;
		if(SmartHealth.curUser.getUserType().equals("MOD")){
			temp = options.length;
		}
		else if(SmartHealth.curUser.getUserType().equals("ADMIN")){
			temp = options.length;
		}
		else{
			temp = options.length - 1;
		}
		if(update(temp)){
			return new LoggedIn(sc);
		}
		return this;
	}
	
	boolean update(int l){
		System.out.println("Enter the detail to update");
		for(int i=0;i<l;i++){
			System.out.println((i + 1) + ". " + options[i]);
		}
		int choice = sc.nextInt();
		if(choice > l || choice < 1){
			System.out.println("Invalid choice. Please enter a Valid choice.");
			return false;
		}
		String s="";
		if(choice != 7) System.out.println("Enter new " + options[choice] + " : ");
		if(choice != 6 && choice != 7) s = sc.next();
		switch(choice){
		case 1 : 
			SmartHealth.curUser.setFirstName(s);
			break;
		case 2 : 
			SmartHealth.curUser.setLastName(s);
			break;
		case 3 : 
			SmartHealth.curUser.setSecondaryEmail(s);
			break;
		case 4 : 
			SmartHealth.curUser.setPassword(s);
			break;
		case 5 : 
			SmartHealth.curUser.setPostalAddress(s);
			break;
		case 6 : 
			s = sc.nextLine();
			SmartHealth.curUser.setAboutMe(s);
			break;
		case 7 : 
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
			urls[ch] = modurl;
			SmartHealth.curUser.setPicURL(urls);
			break;
		case 8 : 
			if(SmartHealth.curUser.getUserType().equals("ADMIN")){
				Admin admin = (Admin)SmartHealth.curUser;
				admin.setEmergencyContact(s);
			}
			else if(SmartHealth.curUser.getUserType().equals("MOD")){
				Moderator moderator = (Moderator)SmartHealth.curUser;
				moderator.setEmergencyContact(s);
			}
			break;
		default : System.out.println("Invalid Choice. Please enter a valid choice");
			return false;
		}
		return true;
	}
	
	Update(Scanner sc){
		super(sc);
	}

}
