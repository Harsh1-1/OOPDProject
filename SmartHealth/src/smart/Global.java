package smart;

/**
 * Global class contains variables which are required globally by application.
 */
public final class Global {
	//Connection String
	public static final String connectionString = "jdbc:mysql://localhost:3306/smarthealthdb?user=root&useSSL=false";
	//No object of this class should be made
	private Global(){
	}
}
