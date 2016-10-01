package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Comment;
import beans.Forum;
import beans.Moderator;
import beans.Post;
import smart.Global;

public class Forums {
	public boolean isClosed(int forumID){
		String query = "SELECT WhenCreated FROM forum WHERE forumID = " + forumID + ";";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query)){
			rs.next();
			rs.getString("WhenCreated");
			if(rs.wasNull()) return true;
			return false;
		}
		catch(SQLException ex){
			System.out.println("Could not check the status of forum.");
			ex.getMessage();
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean closeForum(int forumID, Moderator moderator){
		String query = "UPDATE Forum set WhenClosed = NOW(), DeletedByModerator_Username = "
				+ "'" + moderator.getUserId() + "' "
				+ "WHERE forumID = " + forumID;
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement()){
			s.executeUpdate(query);
			return true;
		}
		catch(SQLException ex){
			System.out.println("Problem Occured while closing the forum");
			ex.getMessage();
			ex.printStackTrace();
			return false;
		}
	}
	
	public Forum getForumContent(int forumID){
		String query = "SELECT * FROM Forum WHERE ForumID = " + forumID + ";";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query)){
			Forum forum = new Forum(rs.getInt("ForumID"),rs.getString("Topic"), 
					rs.getString("URL"), rs.getString("Summary"), rs.getString("WhenCreated"), 
					rs.getString("CreatedByModerator_Username"), getPosts(forumID), 
					rs.getString("WhenClosed"), rs.getString("DeletedByModerator_Username"));
			return forum;
		}
		catch(SQLException ex){
			System.out.println("Error occurred while retreiving the forum");
			ex.getMessage();
			ex.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<Post> getPosts(int forumID){
		ArrayList<Post> posts = new ArrayList<Post>();
		String query = "SELECT * FROM Post WHERE ForumID = " + forumID + ";";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query)){
			int id = 1;
			while(rs.next()){
				Post p = new Post(rs.getString("Username"), rs.getString("TimeCreated"), 
						rs.getString("TextEntry"), rs.getString("PhotoLocation"), 
						rs.getString("LinkLocation"), rs.getString("VideoLocation"), 
						getComments(rs.getString("Username"), rs.getString("TimeCreated")), 
						postRating(rs.getString("Username"), rs.getString("TimeCreated")), id);
				posts.add(p);
				id+=1;
			}
			return posts;
		}
		catch(SQLException ex){
			System.out.println("Error occurred while retreiving posts of this forum");
			ex.getMessage();
			ex.printStackTrace();
			return null;
		}
	}
	
	private ArrayList<Comment> getComments(String postUserName, String postTimeCreated){
		ArrayList<Comment> comments = new ArrayList<Comment>();
		String query = "SELECT * FROM Comment WHERE Post_Username = "
				+ "'" + postUserName + "' AND Post_TimeCreated = '" + postTimeCreated + "';";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query)){
			while(rs.next()){
				comments.add(new Comment(rs.getString("Commenter_Username"), 
						rs.getString("CommentTime"), rs.getString("CommentText"), 
						rs.getString("PhotoLocation"), rs.getString("LinkLocation"), 
						rs.getString("VideoLocation")));
			}
			return comments;
		}
		catch(SQLException ex){
			System.out.println("Problem Occured while retreiving rating of posts");
			ex.getMessage();
			ex.printStackTrace();
			return null;
		}
	}
	
	private double postRating(String userName, String timeCreated){
		String query = "SELECT AVG(Stars) FROM Rating WHERE Post_UserName = "
				+ "'" + userName + "' AND Post_TimeCreated = '" + timeCreated + "';";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(query)){
			rs.next();
			double average = rs.getDouble(1);
			if(rs.wasNull()) return 0.0;
			return average;
		}
		catch(SQLException ex){
			System.out.println("Problem Occured while retreiving rating of posts");
			ex.getMessage();
			ex.printStackTrace();
			return -1.0;
		}
	}
	
	public void commentOnPost(String postUserName, String postTimeCreated, 
			String commenter, String text, String photoLocation, String linkLocation, 
			String videoLocation){
		String query = "INSERT INTO Comment VALUES("
				+ "'" + postUserName + "', "
				+ "'" + postTimeCreated + "', "
				+ "'" + commenter + "', "
				+ "NOW()" + ", "
				+ "'" + text + "', "
				+ "'" + photoLocation + "', "
				+ "'" + linkLocation + "', "
				+ "'" + videoLocation + "');";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement()){
			s.executeUpdate(query);
		}
		catch(SQLException ex){
			System.out.println("Could not insert comment");
			ex.getMessage();
			ex.printStackTrace();
		}
	}
	
	public void ratePost(String postUserName, String postTimeCreated, String rater, 
			int stars){
		String query = "INSERT INTO Rating VALUES("
				+ "'" + postUserName + "', "
				+ "'" + postTimeCreated + "', "
				+ "'" + rater + "', "
				+ stars + ");";
		try(Connection con = DriverManager.getConnection(Global.connectionString);
				Statement s = con.createStatement()){
			s.executeUpdate(query);
		}
		catch(SQLException ex){
			System.out.println("Could not rate post");
			ex.getMessage();
			ex.printStackTrace();
		}
	}
}
