package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.ForumIdentifier;
import smart.Global;

public class ForumList {
	public ArrayList<ForumIdentifier> listForums(){
		String query = "SELECT forumID, topic FROM forums;";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query)){
			ArrayList<ForumIdentifier> forums = new ArrayList<ForumIdentifier>();
			while(rs.next()){
				forums.add(new ForumIdentifier(rs.getInt("forumID"), 
						rs.getString("topic")));
			}
			return forums;
		}
		catch(SQLException ex){
			System.out.println("Could not retreive available forums.");
			ex.getMessage();
			ex.printStackTrace();
			return null;
		}
	}
}
