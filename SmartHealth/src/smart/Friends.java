package smart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.Date;

public class Friends extends State{
	
	private String host ="jdbc:mysql://localhost:3306/smarthealthdb";
	private String Username = "root";
	private String Password = "";

	Friends(Scanner sc)
	{
		super(sc);
	}
	
	State handle()
	{
		System.out.println("What you want to do:");
		System.out.println("1. View Friends");
		System.out.println("2. Send Friend Request");
		System.out.println("3. View pending requests");
		System.out.println("4. Unfriend a friend");
		System.out.println("5. Go back to profile page");
		
		int choice = sc.nextInt();
		
		switch(choice)
		{
		case 1:
			viewFriends();
			break;
		case 2:
			System.out.println("Enter the username of the user,"
					+ "you want to add as a friend: ");
		String UserName = sc.nextLine();
		if(sendFriendRequest(UserName))
			System.out.println("Friend request sent successfully to:" + UserName);
		else
			System.out.println("The above error occured while sending request!!");
			break;
		case 3: 
			viewPendingRequests();
			break;
		case 4:
			unfriend();
			break;
		case 5:
			return new LoggedIn(sc);
		default: System.out.println("Invalid choice entered");
		}
		 //just to avoid error as of now
		return this;
	}
	
	private void viewFriends()
	{
		try {	
			
			Connection con = DriverManager.getConnection(host,Username,Password);
			
			
			Statement stmt = con.createStatement();
			String SQL = "select Requester_Username from friendship where Requested_Username = " 
						+ "'" + SmartHealth.curUser.getUserId() + "'" + " and WhenConfirmed IS NOT NULL and WhenRejected IS NULL";
			ResultSet result = stmt.executeQuery(SQL);
			
			while(result.next())
			{
				String FriendUserName = result.getString("Requester_Username");
				System.out.println(FriendUserName);
			}
			
			
			SQL = "select Requested_Username from friendship where Requester_Username = " 
					+ "'" + SmartHealth.curUser.getUserId() + "'" + " and WhenConfirmed IS NOT NULL and WhenRejected IS NULL";
			
			result = stmt.executeQuery(SQL);
			
			while(result.next())
			{
				String FriendUserName = result.getString("Requester_Username");
				System.out.println(FriendUserName);
			}
			
			result.close();
			stmt.close();
			con.close();
		}
		catch ( SQLException err) {
		System.out.println(err.getMessage( ));
	}

	}
	
	private boolean ifUserExists(String Username)
	{
		try
		{
			Connection con = DriverManager.getConnection(host,Username,Password);
			Statement stmt = con.createStatement();
			String SQL = "select Username from user where Username=" + "'" + Username + "'";
			ResultSet result = stmt.executeQuery(SQL);
			
			boolean ifuserexists =  result.isBeforeFirst();
			result.close();
			stmt.close();
			con.close();
			return ifuserexists;
		}
		
		catch ( SQLException err) {
			System.out.println(err.getMessage( ));
		}
		return false;
	}
	
	private boolean isalreadyafriend(String UserName)
	{
		try
		{
			Connection con = DriverManager.getConnection(host,Username,Password);
			Statement stmt = con.createStatement();
			String SQL = "select Requester_Username from friendship where Requested_Username = " 
					+ "'" + SmartHealth.curUser.getUserId() + "'" + " and WhenConfirmed IS NOT NULL and WhenRejected IS NULL" + " and Requester_Username = " + "'" + UserName + "'";
			
			ResultSet result = stmt.executeQuery(SQL);
			boolean ifalreadyafriendtest1 =  result.isBeforeFirst();
			
			SQL = "select Requested_Username from friendship where Requester_Username = " 
					+ "'" + SmartHealth.curUser.getUserId() + "'" + " and WhenConfirmed IS NOT NULL and WhenRejected IS NULL" + " and Requested_Username = " + "'" + UserName + "'";
			
			result = stmt.executeQuery(SQL);
			boolean ifalreadyafriendtest2 =  result.isBeforeFirst();
			
			result.close();
			stmt.close();
			con.close();
			return (ifalreadyafriendtest1 || ifalreadyafriendtest2);
			
		}
		catch ( SQLException err) {
			System.out.println(err.getMessage( ));
		}
		
		return false;
	}
	private boolean sendFriendRequest(String UserName)
	{
		if(ifUserExists(UserName))
		{
			if(isalreadyafriend(UserName))
			{
				System.out.println(UserName + " is already your friend!!");
				return false;
			}
			//Still need to check if a requester-requested pair already exist in friendship table
			else if(checkUnconfirmedEntry(UserName))
			{
				try{
					Connection con = DriverManager.getConnection(host,Username,Password);
					Statement stmt = con.createStatement();
					Date date = new Date();
					Timestamp timestamp = new Timestamp(date.getTime());
					String SQL = "Update table friendship set WhenRequested = " + timestamp + " where Requester_Username = '" + SmartHealth.curUser.getUserId()
					  			+ "' and Requested_Username = '" + UserName + "'";
					
					int rowinserted = stmt.executeUpdate(SQL);
					
					stmt.close();
					con.close();
					
					if(rowinserted == 0)
					{
						System.out.println("Failed to send request!!");
						return false;
					}						
					else
					return true;
					
				}
				catch ( SQLException err) {
					System.out.println(err.getMessage( ));
				}
			}
			else
			{
				try
				{
					Connection con = DriverManager.getConnection(host,Username,Password);
					Statement stmt = con.createStatement();
					Date date = new Date();
					Timestamp timestamp = new Timestamp(date.getTime());
					String SQL = "INSERT INTO friendship values('" + SmartHealth.curUser.getUserId() + "'," 
							+ "','" + UserName + "'," + timestamp + ",NULL ,NULL ,NULL ,NULL )";
					int rowinserted = stmt.executeUpdate(SQL);
					
					stmt.close();
					con.close();
					
					if(rowinserted == 0)
					{
						System.out.println("Failed to send request!!");
						return false;
					}						
					else
					return true;
				}
							
				catch ( SQLException err) {
					System.out.println(err.getMessage( ));
				}
			}
		}
		else
		{
			System.out.println("Username doesn't exists!!");
			return false;
		}
		return false;
	}
	
	private void unfriend()
	{
		System.out.println("Here are your all friends:");
		viewFriends();
		System.out.println("Enter username of friend you want to unfriend:");
		String UserName = sc.nextLine();
		if(ifUserExists(UserName))
		{
			if(isalreadyafriend(UserName))
			{
				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());
				
				try
				{
					Connection con = DriverManager.getConnection(host,Username,Password);
					Statement stmt = con.createStatement();
					String SQL = "update table friendship set WhenConfirmed = NULL, WhenUnfriended = " + timestamp
								 + "where Requester_Username = " + "'" + SmartHealth.curUser.getUserId() + "'" 
								 + " and Requested_Username = " + "'" + UserName + "'";
					int result = stmt.executeUpdate(SQL);
					
					if(result != 0)
						System.out.println("Unfriended Successfully!!");
					
					
							SQL = "update table friendship set WhenConfirmed = NULL, WhenUnfriended = " + timestamp
							 + "where Requested_Username = " + "'" + SmartHealth.curUser.getUserId() + "'" 
							 + " and Requester_Username = " + "'" + UserName + "'";
							result = stmt.executeUpdate(SQL);
				
							if(result == 0)
								System.out.println("Unfriended Successfully");
					
					stmt.close();
					con.close();
				}
				
				catch ( SQLException err) {
					System.out.println(err.getMessage( ));
				}
				
				
			}
			else
				System.out.println("You cannot unfriend someone who is not your friend");
		}
		else
		{
			System.out.println("UserName does not exist!!");
		}
	}
	
	private void viewPendingRequests()
	{
		try{
			Connection con = DriverManager.getConnection(host,Username,Password);
			Statement stmt = con.createStatement();
			String SQL = "Select Requester_Username from friendship where Requested_Username = '" + SmartHealth.curUser.getUserId() + "'" 
					 + " and WhenConfirmed IS NULL and WhenRejected IS NULL";
			
			ResultSet result = stmt.executeQuery(SQL);
			boolean arependingrequests =  result.isBeforeFirst();
			if(!arependingrequests)
			{
				System.out.println("There are no pending requests :D");
			}
			else
			{
				while(result.next())
				{
					String Requester_UserName = result.getString("Requester_Username");
					System.out.println("1. Accept " + Requester_UserName + " as friend");
					System.out.println("2. Reject " + Requester_UserName + " as friend");
					System.out.println("3. Cancel");
					System.out.println("Enter your choice:");
					int choice = sc.nextInt();
					Date date = new Date();
					Timestamp timestamp = new Timestamp(date.getTime());
					switch(choice)
					{
					case 1:
						
						String AcceptQuery = "Update table friendship set WhenConfirmed = " + timestamp + " where Requester_Username = '" + Requester_UserName + "'"
											+ " and Requested_Username = '" + SmartHealth.curUser.getUserId() + "'";
						int Accept = stmt.executeUpdate(AcceptQuery);
						if(Accept == 0)
							System.out.println("Failed to process Accept Request");
						
						break;
					case 2:
						
						String RejectQuery = "Update table friendship set WhenRejected = " + timestamp + " where Requester_Username = '" + Requester_UserName + "'"
								+ " and Requested_Username = '" + SmartHealth.curUser.getUserId() + "'";
						int Reject = stmt.executeUpdate(RejectQuery);
						if(Reject == 0)
							System.out.println("Failed to process reject request");
						
						break;
					default:System.out.println("pending request window closed x");
					}
				}
			}
			
			result.close();
			stmt.close();
			con.close();
			
		}
		catch ( SQLException err) {
			System.out.println(err.getMessage( ));
		}
		
	}
	
	private boolean checkUnconfirmedEntry(String UserName)
	{
		
		try
		{
			Connection con = DriverManager.getConnection(host,Username,Password);
			Statement stmt = con.createStatement();
			//String SQL = "select Requester_Username,Requested_Username from friendship where (Requester_Username = '" + UserName + "'"
			//		      + " and Requested_Username = '" + SmartHealth.curUser.getUserId() + "') OR ( Requester_Username = '" + SmartHealth.curUser.getUserId() + "'"
			//			  + " and Requested_Username = '" + UserName + "')";
			
			String SQL = "Select Requester_Username,Requested_Username from friendship where Requester_Username = '" + SmartHealth.curUser.getUserId() + "'"
					     + " and Requested_Username = '" + UserName + "'" + " and WhenConfirmed is NULL";
			ResultSet result = stmt.executeQuery(SQL);
			
			boolean ifpairexists =  result.isBeforeFirst();
			result.close();
			stmt.close();
			con.close();
			return ifpairexists;
		}
		
		catch ( SQLException err) {
			System.out.println(err.getMessage( ));
		}
		return false;
	}
	
}
