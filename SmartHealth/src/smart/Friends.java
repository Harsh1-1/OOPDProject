package smart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
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
			if(con != null)
				System.out.println("Connected Successfully");
			
			Statement stmt = con.createStatement();
			String SQL = "select Requester_Username from friendship where Requested_Username = " 
						+ "'" + SmartHealth.curUser.getUserId() + "'" + " and WhenConfirmed >= WhenUnfriended";
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
}
