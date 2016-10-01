package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.ForumIdentifier;
import beans.Moderator;
import smart.Global;

public class ForumList {
	public ArrayList<ForumIdentifier> listForums(){
		String query = "SELECT forumID, topic FROM forum ORDER BY forumID;";
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
	
	public ArrayList<ForumIdentifier> listOpenForums(){
		String query = "SELECT forumID, topic FROM forum WHERE WhenClosed IS NULL ORDER BY forumID;";
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
	
	public void createForum(String topic, String summary, Moderator moderator){
		int ID = numIDs() + 1;
		String url = "www.smarthealth.com/forums/" + ID;
		if(ID != -1){
			String query = "INSERT INTO Forum VALUES ("
					+ ID + ", " 
					+ "'" + topic + "',"
					+ "'" + url + "',"
					+ "'" + summary + "',"
					+ "NOW()" + ","
					+ "NULL" + ","
					+ "'" + moderator.getUserId() + "',"
					+ "NULL" + ");";
			try(Connection con = DriverManager.getConnection(Global.connectionString);
					Statement s = con.createStatement()){
				s.executeUpdate(query);
			}
			catch(SQLException ex){
				System.out.println("Forum Creation failed.");
				ex.getMessage();
				ex.printStackTrace();
			}
		}
	}
	
	public int numIDs(){
		String query = "SELECT COUNT(*) FROM forum;";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query)){
			rs.next();
			int count = rs.getInt(1);
			return count;
		}
		catch(SQLException ex){
			System.out.println("Could not retreive available forums.");
			ex.getMessage();
			ex.printStackTrace();
			return -1;
		}
	}
}
