package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Admin;
import beans.EndUser;
import beans.Moderator;
import beans.Qualification;
import beans.User;
import smart.Global;

public class SignUp implements UserForm {

	public boolean userIDExists(String userID){
		boolean userExists = false;
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery("Select 1 from User where Username = "
						+ "'" + userID + "';")){
			if(rs.isBeforeFirst()){
				userExists = true;
			}
		}
		catch(SQLException ex){
			System.out.println("Some error occured while checking for username");
			ex.getMessage();
			ex.printStackTrace();
		}
		return userExists;
	}
	
	public boolean primaryEmailIDExists(String emailID){
		boolean emailIDExists = false;
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery("Select 1 from User where Email1 = "
						+ "'" + emailID + "';")){
			if(rs.isBeforeFirst()){
				emailIDExists = true;
			}
		}
		catch(SQLException ex){
			System.out.println("Some error occured while checking for EmailID");
			ex.getMessage();
			ex.printStackTrace();
		}
		return emailIDExists;
	}

	public boolean store(User user){
		if(userIDExists(user.getUserId())){
			System.out.println("User Id already taken");
			return false;
		}
		else if(primaryEmailIDExists(user.getPrimaryEmail())){
			System.out.println("Email ID already taken");
			return false;
		}
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement()){
			String usertype;
			if(user.getUserType().equals("ENDUSER")){
				usertype = "NEW";
			}
			else{
				usertype = user.getUserType();
			}
			int typeID;
			ResultSet rs = s.executeQuery("Select UserTypeID from UserType where Description = '" + usertype + "';");
			rs.next();
			typeID = rs.getInt("UserTypeID");
			int status = user.hasQuit()?0:1;
			String query = "Insert into user values(" + 
					"'" + user.getUserId() + "'," +
					"'" + user.getPassword() + "'," +
					"'" + user.getPrimaryEmail() + "'," +
					"'" + user.getSecondaryEmail() + "'," +
					"'" + user.getFirstName() + "'," +
					"'" + user.getLastName() + "'," +
					"'" + user.getAboutMe() + "'," +
					"'" + user.getPicURL()[0] + "'," +
					"'" + user.getPicURL()[1] + "'," +
					"'" + user.getPicURL()[2] + "'," +
					"'" + user.getPostalAddress().getStreetNumber() + "'," +
					"'" + user.getPostalAddress().getStreetName() + "'," +
					"'" + user.getPostalAddress().getMajorMunicipality() + "'," +
					"'" + user.getPostalAddress().getGoverningDistrict() + "'," +
					"'" + user.getPostalAddress().getPostalArea() + "'," +
					"" + typeID + "," +
					+ status + 
					");";
			s.executeUpdate(query);
			if(usertype.equals("NEW")){
				String query2 = "Insert into EndUser values(" + 
						"'" + user.getUserId() + "'," +
						((EndUser)user).getKarma() + "," + 
						"CURDATE()" + 
						");";
				s.executeUpdate(query2);
			}else if(usertype.equals("ADMIN")){
				String query2 = "Insert into Administrator values(" + 
						"'" + user.getUserId() + "'," +
						"'" + ((Admin)user).getEmergencyContact() + "'" + 
						");";
				s.executeUpdate(query2);
			}else if(usertype.equals("MOD")){
				String query2 = "Insert into Moderator values(" + 
						"'" + user.getUserId() + "'," +
						"'" + ((Moderator)user).getEmergencyContact() + "'" +
						");";
				s.executeUpdate(query2);
				
				ArrayList<Qualification> qualifications = ((Moderator)user).getQualifications();
				for(Qualification q : qualifications){
					String query3 = "Insert into ModeratorQualification values(" 
							+ q.getQualificationID() + ","
							+ "'" + user.getUserId() + "'," 
							+ "NOW()" +
							");";
					s.executeUpdate(query3);
				}
			}
		}
		catch(SQLException ex){
			System.out.println("Some error occured while entering information of user.");
			ex.getMessage();
			ex.printStackTrace();
			return false;
		}
		return true;
	}
}
