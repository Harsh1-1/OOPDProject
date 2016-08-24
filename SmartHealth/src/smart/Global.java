package smart;

import java.util.ArrayList;
/**
 * Global class contains variables which are required globally by application.
 */
final class Global {
	//Database of current Users
	static ArrayList<User> users = new ArrayList<User>();
	//Accepted Qualifications
	static final String[] acceptedQualifications = {"", "MD", "MBBS", "MS", "MMSC"};
	//No object of this class should be made
	private Global(){
	}
}
