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
			break;
		case 4:
			break;
		default: System.out.println("Invalid choice entered");
		return this;
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
						+ "'" + SmartHealth.curUser.getUserId() + "'" + " and WhenConfirmed >= WhenUnfriended and WhenRejected IS NULL";
			ResultSet result = stmt.executeQuery(SQL);
			
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
					+ "'" + SmartHealth.curUser.getUserId() + "'" 
					+ " and WhenConfirmed >= WhenUnfriended and WhenRejected IS NULL"
					+ " and Requester_Username = " + "'" + UserName + "'";
			
			ResultSet result = stmt.executeQuery(SQL);
			boolean ifalreadyafriend =  result.isBeforeFirst();
			result.close();
			stmt.close();
			con.close();
			return ifalreadyafriend;
			
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
			else
			{
				try
				{
					Connection con = DriverManager.getConnection(host,Username,Password);
					Statement stmt = con.createStatement();
					Date date = new Date();
					Timestamp timestamp = new Timestamp(date.getTime());
					String SQL = "INSERT INTO friendship values('" + SmartHealth.curUser.getUserId() + "'," 
							+ "'" + UserName + "'," + timestamp + ", , , , )";
					int rowinserted = stmt.executeUpdate(SQL);
					
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
}
