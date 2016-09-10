package smart;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

interface UserForm {
	default boolean isValidEmail(String email){
		String pattern = "^.+@.+$";
		return Pattern.matches(pattern, email);
	}
	default boolean isValidContactNumber(String contact){
		String pattern = "^[0-9]+$";
		return Pattern.matches(pattern, contact);
	}
	default boolean isValidURL(String URL){
		try{
			URL url = new URL(URL);
			url.toURI();
			return true;
		}
		catch(Exception ex){
			System.out.println("Could not verify URL");
		}
		return false;
	}
	default ArrayList<Qualification> getQualifications(){
		ArrayList<Qualification> qualifications = new ArrayList<Qualification>();
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery("Select * from Qualification") )
		{
			while(rs.next()){
				qualifications.add(
						new Qualification(rs.getInt("QualificationID"),rs.getString("Description")));
			}
			con.close();
		}
		catch(SQLException ex){
			System.out.println("Some error occured while retreiving available qualifications");
			ex.getMessage();
		}
		return qualifications;
	}
	
}
