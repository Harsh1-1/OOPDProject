package smart;

import java.util.ArrayList;

final class Global {
	static ArrayList<User> users;
	static final String[] acceptedQualifications = {};
	private Global(){
		users = new ArrayList<User>();
	}
}
