package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Address;
import beans.Qualification;
import smart.Global;

public class Update implements UserForm{

	public void updateHelper(String table, String field, String newval, String userName){
		String query = "UPDATE " + table + " SET " + field + " = '" + newval + 
				"' WHERE UserName = '" + userName + "';";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
			Statement s = con.createStatement() )
		{
			s.executeUpdate(query);
		}
		catch(SQLException ex){
			System.out.println("Update failed");
			ex.getMessage();
			ex.printStackTrace();
		}
	}
	
	public void updateAddressHelper(Address address, String userID){
		String query[] = new String[5];
		query[0] = "UPDATE User SET StreetNumber = '" + address.getStreetNumber() +
				"' WHERE UserName = '" + userID + "';";
		query[1] = "UPDATE User SET StreetName = '" + address.getStreetName() +
				"' WHERE UserName = '" + userID + "';";
		query[2] = "UPDATE User SET MajorMunicipality = '" + address.getMajorMunicipality() +
				"' WHERE UserName = '" + userID + "';";
		query[3] = "UPDATE User SET GoverningDistrict = '" + address.getGoverningDistrict() +
				"' WHERE UserName = '" + userID + "';";
		query[4] = "UPDATE User SET PostalArea = '" + address.getPostalArea() +
				"' WHERE UserName = '" + userID + "';";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement() )
			{
				for(String q : query) s.executeUpdate(q);
			}
			catch(SQLException ex){
				System.out.println("Updating address failed");
				ex.getMessage();
				ex.printStackTrace();
			}
	}
	
	public void updateModeratorQualificationHelper(ArrayList<Qualification> qualifications, String userID){
		String deleteQuery = "DELETE FROM ModeratorQualification WHERE UserName = '" + userID + "';";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement() )
			{
				s.executeUpdate(deleteQuery);
				for(Qualification q : qualifications){
					String query = "Insert into ModeratorQualification values(" 
							+ q.getQualificationID() + ","
							+ "'" + userID + "'," 
							+ "NOW()" +
							");";
					s.executeUpdate(query);
				}
			}
			catch(SQLException ex){
				System.out.println("Updating qualifications failed");
				ex.getMessage();
				ex.printStackTrace();
			}
	}
	
}
