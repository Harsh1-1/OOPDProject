package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import smart.Global;

public class LoggedIn {

	public void quitUser(String userID){
		String query = "UPDATE User SET Status = 0 WHERE UserName = '" + userID + "';";
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
	
	public void joinUser(String userID){
		String query = "UPDATE User SET Status = 1 WHERE UserName = '" + userID + "';";
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
	
}
