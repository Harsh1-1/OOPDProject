package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Address;
import beans.Admin;
import beans.EndUser;
import beans.Moderator;
import beans.Qualification;
import beans.User;
import smart.Global;

public class Login {
	
	private final static int USERID = 0;
	private final static int PASSWORD = 1;
	private final static int PRIMARY_EMAIL = 2;
	private final static int SECONDARY_EMAIL = 3;
	private final static int FIRST_NAME = 4;
	private final static int LAST_NAME = 5;
	private final static int ABOUT_ME = 6;
	private final static int PROFILE_PIC1 = 7;
	private final static int PROFILE_PIC2 = 8;
	private final static int PROFILE_PIC3 = 9;
	private final static int STREET_NUMBER = 10;
	private final static int STREET_NAME = 11;
	private final static int MAJOR_MUNICIPALITY = 12;
	private final static int GOVERNING_DISTRICT = 13;
	private final static int POSTAL_AREA = 14;

	private String
	commonDetails[] = new String[15];
	
	/**
	 * returns the user if valid user else null
	 * @param id The users primary mail ID
	 * @param password The users password
	 * @return The user if present in database else null
	 */
	public User validUser(String id, String password){
		String query = "Select * from user where Email1 = '" + id + 
				"' and Password = '" + password + "';";
		User user = null;
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();)
		{
			ResultSet rsUserDetails = s.executeQuery(query);
			if(!rsUserDetails.isBeforeFirst()){
				rsUserDetails.close();
				return null;
			}
			rsUserDetails.next();
			for(int i = 0;i<commonDetails.length;i++){
				commonDetails[i] = rsUserDetails.getString(i+1);
			}
			int typeID = rsUserDetails.getInt("UserTypeID");
			int quit = rsUserDetails.getInt("Status");
			boolean hasQuit = (quit==0)?true:false;
			rsUserDetails.close();
			
			ResultSet rsUserType = s.executeQuery("Select Description from UserType where UserTypeID = "
			+ typeID + ";");
			rsUserType.next();
			String type = rsUserType.getString("Description");
			rsUserType.close();
			
			if(type.equals("MOD")){
				ResultSet rsModerator = s.executeQuery("Select * from Moderator where UserName = '" 
						+ commonDetails[USERID]+ "'");
				rsModerator.next();
				String emergencyContact = rsModerator.getString("Phone");
				rsModerator.close();
				ArrayList<Qualification> qualifications = new ArrayList<Qualification>();
				
				ResultSet rsQualifications = s.executeQuery("Select Qualification.QualificationID, "
						+ "Qualification.Description from Qualification INNER JOIN "
						+ "ModeratorQualification ON "
						+ "Qualification.QualificationID = ModeratorQualification.QualificationID "
						+ "WHERE ModeratorQualification.Username = '" + commonDetails[USERID] + "';");
				while(rsQualifications.next()){
					qualifications.add(new Qualification(rsQualifications.getInt(1),rsQualifications.getString(2)));
				}
				
				user = new Moderator(commonDetails[FIRST_NAME],commonDetails[LAST_NAME],commonDetails[PRIMARY_EMAIL],
						commonDetails[SECONDARY_EMAIL],commonDetails[PASSWORD],commonDetails[USERID],
						new Address(commonDetails[STREET_NUMBER], commonDetails[STREET_NAME],
								commonDetails[MAJOR_MUNICIPALITY], commonDetails[GOVERNING_DISTRICT], 
								commonDetails[POSTAL_AREA]), 
						commonDetails[ABOUT_ME],commonDetails[PROFILE_PIC1],
						commonDetails[PROFILE_PIC2], commonDetails[PROFILE_PIC3], hasQuit, 
						emergencyContact, qualifications);
			}else if(type.equals("ADMIN")){
				ResultSet rsAdmin = s.executeQuery("Select * from Administrator where UserName = '" 
						+ commonDetails[USERID]+ "'");
				rsAdmin.next();
				String emergencyContact = rsAdmin.getString("Phone");
				rsAdmin.close();
				user = new Admin(commonDetails[FIRST_NAME],commonDetails[LAST_NAME],commonDetails[PRIMARY_EMAIL],
						commonDetails[SECONDARY_EMAIL],commonDetails[PASSWORD],commonDetails[USERID],
						new Address(commonDetails[STREET_NUMBER], commonDetails[STREET_NAME],
								commonDetails[MAJOR_MUNICIPALITY], commonDetails[GOVERNING_DISTRICT], 
								commonDetails[POSTAL_AREA]), 
						commonDetails[ABOUT_ME],commonDetails[PROFILE_PIC1],
						commonDetails[PROFILE_PIC2], commonDetails[PROFILE_PIC3], hasQuit, 
						emergencyContact);
			}else{
				increaseKarma(commonDetails[USERID]);
				ResultSet rsEndUser = s.executeQuery("Select DATEDIFF(CURDATE(), DateCreated), Karma from EndUser where UserName = '" 
						+ commonDetails[USERID]+ "'");
				rsEndUser.next();
				int karma = rsEndUser.getInt("Karma");
				int datedif = rsEndUser.getInt(1);
				rsEndUser.close();
				if(datedif > 365 && !type.equals("OLD")){
					type = "OLD";
					ResultSet rsUserID = s.executeQuery("Select UserTypeID from UserType "
							+ "where Description = '" + type + "';");
					rsUserID.next();
					int newid = rsUserID.getInt(1);
					rsUserID.close();
					s.executeUpdate("UPDATE User SET UserTypeID = " + newid 
							+ " WHERE Username = '" + commonDetails[USERID] + "';");
				}else if(datedif > 31 && !type.equals("MIDDLE")){
					type = "MIDDLE";
					ResultSet rsUserID = s.executeQuery("Select UserTypeID from UserType "
							+ "where Description = '" + type + "';");
					rsUserID.next();
					int newid = rsUserID.getInt(1);
					rsUserID.close();
					s.executeUpdate("UPDATE User SET UserTypeID = " + newid 
							+ " WHERE Username = '" + commonDetails[USERID] + "';");
				}
				user = new EndUser(commonDetails[FIRST_NAME],commonDetails[LAST_NAME],commonDetails[PRIMARY_EMAIL],
						commonDetails[SECONDARY_EMAIL],commonDetails[PASSWORD],commonDetails[USERID],
						new Address(commonDetails[STREET_NUMBER], commonDetails[STREET_NAME],
								commonDetails[MAJOR_MUNICIPALITY], commonDetails[GOVERNING_DISTRICT], 
								commonDetails[POSTAL_AREA]), 
						commonDetails[ABOUT_ME],commonDetails[PROFILE_PIC1],
						commonDetails[PROFILE_PIC2], commonDetails[PROFILE_PIC3],hasQuit);
				user.setUserType(type);
				((EndUser)user).setKarma(karma);
			}
			rsUserDetails.close();
		}
		catch(SQLException ex){
			System.out.println("Some error occured while logging in");
			ex.getMessage();
			ex.printStackTrace();
		}
		return user;
	}
	
	private void increaseKarma(String userID){
		String query = "UPDATE EndUser SET Karma = Karma + 1 WHERE Username = '" + userID + "';";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement()){
			s.executeUpdate(query);
		}
		catch(SQLException ex){
			System.out.println("Could not update Karma");
			ex.getMessage();
			ex.printStackTrace();
		}
	}
}
