package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.User;
import smart.Global;



public class Friends {

	private static views.Friends view = new views.Friends();
	//to view friends
	public void viewFriends(User curUser)
	{
		try {	
			
			Connection con = DriverManager.getConnection(Global.connectionString);
			
			
			Statement stmt = con.createStatement();
			String SQL = "select Requester_Username from friendship where Requested_Username = " 
						+ "'" + curUser.getUserId() + "'" + " and WhenConfirmed IS NOT NULL and WhenRejected IS NULL;";
			ResultSet result = stmt.executeQuery(SQL);
			
			
			
			while(result.next())
			{
				String FriendUserName = result.getString("Requester_Username");
				System.out.println(FriendUserName);
			}
			
			
			SQL = "select Requested_Username from friendship where Requester_Username = " 
					+ "'" + curUser.getUserId() + "'" + " and WhenConfirmed IS NOT NULL and WhenRejected IS NULL;";
			
			result = stmt.executeQuery(SQL);
			
			while(result.next())
			{
				String FriendUserName = result.getString("Requested_Username");
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
	
	
	public boolean checkUnconfirmedEntry(String UserName,User curUser)
	{
		
		try
		{
			Connection con = DriverManager.getConnection(Global.connectionString);
			Statement stmt = con.createStatement();
			//String SQL = "select Requester_Username,Requested_Username from friendship where (Requester_Username = '" + UserName + "'"
			//		      + " and Requested_Username = '" + SmartHealth.curUser.getUserId() + "') OR ( Requester_Username = '" + SmartHealth.curUser.getUserId() + "'"
			//			  + " and Requested_Username = '" + UserName + "');";
			
			String SQL = "Select Requester_Username,Requested_Username from friendship where Requester_Username = '" + curUser.getUserId() + "'"
					     + " and Requested_Username = '" + UserName + "'" + " and WhenConfirmed is NULL;";
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
	
	//to check if a user exists or not
		public boolean ifUserExists(String Username)
		{
			try
			{
				Connection con = DriverManager.getConnection(Global.connectionString);
				Statement stmt = con.createStatement();
				String SQL = "select Username from user where Username=" + "'" + Username + "';";
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
		
		//to check it the user is already a friend
		public boolean isalreadyafriend(String UserName,User curUser)
		{
			try
			{
				Connection con = DriverManager.getConnection(Global.connectionString);
				Statement stmt = con.createStatement();
				String SQL = "select Requester_Username from friendship where Requested_Username = " 
						+ "'" + curUser.getUserId() + "'" + " and WhenConfirmed IS NOT NULL and WhenRejected IS NULL" + " and Requester_Username = " + "'" + UserName + "';";
				
				ResultSet result = stmt.executeQuery(SQL);
				boolean ifalreadyafriendtest1 =  result.isBeforeFirst();
				
				SQL = "select Requested_Username from friendship where Requester_Username = " 
						+ "'" + curUser.getUserId() + "'" + " and WhenConfirmed IS NOT NULL and WhenRejected IS NULL" + " and Requested_Username = " + "'" + UserName + "';";
				
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
		
		public boolean isEndUser(String username)
		{
			try
			{
				Connection con = DriverManager.getConnection(Global.connectionString);
				Statement stmt = con.createStatement();
				String SQL = "select Username from administrator where Username ="
						+ "'" + username + "'";
				
				ResultSet result = stmt.executeQuery(SQL);
				
				if(result.isBeforeFirst())
				{
					boolean isenduser = false;
					return isenduser;
				}
				
				SQL = "select Username from moderator where Username ="
						+ "'" + username + "'";
				
				result = stmt.executeQuery(SQL);
				
				if(result.isBeforeFirst())
				{
					boolean isenduser = false;
					return isenduser;
				}
				
			}
			
			
			catch ( SQLException err) {
				System.out.println(err.getMessage( ));
			}
			
			return true;
		}
		
		public boolean sendFriendRequest(String UserName,User curUser)
		{
		 if(checkUnconfirmedEntry(UserName,curUser))
			{
				try{
					Connection con = DriverManager.getConnection(Global.connectionString);
					Statement stmt = con.createStatement();
					String SQL = "Update friendship set WhenRequested = " + "NOW()" + " where Requester_Username = '" + curUser.getUserId()
					  			+ "' and Requested_Username = '" + UserName + "';";
					
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
					Connection con = DriverManager.getConnection(Global.connectionString);
					Statement stmt = con.createStatement();
					String SQL = "INSERT INTO friendship values('" + curUser.getUserId() + "'," 
							+ "'" + UserName + "'," + "NOW()" + ",NULL ,NULL ,NULL ,NULL );";
					
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
		 return false;
		}
		
		public void unfriend(String UserName, User curUser)
		{
			try
			{
				Connection con = DriverManager.getConnection(Global.connectionString);
				Statement stmt = con.createStatement();
				String SQL = "update friendship set WhenConfirmed = NULL, WhenUnfriended = " + "NOW()"
							 + "where Requester_Username = " + "'" + curUser.getUserId() + "'" 
							 + " and Requested_Username = " + "'" + UserName + "';";
				int result = stmt.executeUpdate(SQL);
				
				if(result != 0)
					System.out.println("Unfriended Successfully!!");
				
				
						SQL = "update friendship set WhenConfirmed = NULL, WhenUnfriended = " + "NOW()"
						 + "where Requested_Username = " + "'" + curUser.getUserId() + "'" 
						 + " and Requester_Username = " + "'" + UserName + "';";
						result = stmt.executeUpdate(SQL);
			
						if(result != 0)
							System.out.println("Unfriended Successfully");
				
				stmt.close();
				con.close();
			}
			
			catch ( SQLException err) {
				System.out.println(err.getMessage( ));
			}	
		}
		
		//to withdraw requests
		
		public void withdrawRequests(User curUser)
		{
			try
			{
				Connection con = DriverManager.getConnection(Global.connectionString);
				Statement stmt = con.createStatement();
				System.out.println("you have sent requests to all these users");
				String SQL = "select Requested_Username from friendship where Requester_Username = '" + curUser.getUserId() + "'"
						    + " and WhenRequested IS NOT NULL and WhenConfirmed IS NULL;";
				ResultSet result = stmt.executeQuery(SQL);
				
				int choice;
				
				while(result.next())
				{
					String RequestedUserName = result.getString("Requested_Username");
					System.out.println(RequestedUserName);
					choice = view.getwithdrawchoice();
					if(choice == 1)
					{
						String WithdrawQuery = "Update friendship set WhenRequested = NULL, WhenWithdrawn = " + "NOW()" + " where Requester_Username = '" + curUser.getUserId() + "'"
					              + " and Requested_Username = '" + RequestedUserName + "';"; 
						
						// System.out.println(WithdrawQuery);
						int withdrawcheck = stmt.executeUpdate(WithdrawQuery);
						
						if(withdrawcheck == 0)
							System.out.println("failed to withdraw request");
						else
							System.out.println("Request withdrawn successfully :)");
					}
					else if(choice == 0)
						System.out.println("request not withdrawn");
					else
						System.out.println("wrong input entered");
					
					
				}
				result.close();
				stmt.close();
				con.close();
			}
			catch ( SQLException err) {
				System.out.println(err.getMessage( ));
			}
		}
		
		public void viewPendingRequests(User curUser)
		{
			try{
				Connection con = DriverManager.getConnection(Global.connectionString);
				Statement stmt = con.createStatement();
				String SQL = "Select Requester_Username from friendship where Requested_Username = '" + curUser.getUserId() + "'" 
						 + " and WhenConfirmed IS NULL and WhenRejected IS NULL;";
				
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
						
						switch(view.acceptreject(Requester_UserName))
						{
						case 1:
							
							String AcceptQuery = "Update friendship set WhenConfirmed = " + "NOW()" + " where Requester_Username = '" + Requester_UserName + "'"
												+ " and Requested_Username = '" + curUser.getUserId() + "';";
							int Accept = stmt.executeUpdate(AcceptQuery);
							if(Accept == 0)
								System.out.println("Failed to process Accept Request");
							
							break;
						case 2:
							
							String RejectQuery = "Update friendship set WhenRejected = " + "NOW()" + " where Requester_Username = '" + Requester_UserName + "'"
									+ " and Requested_Username = '" + curUser.getUserId() + "';";
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
	
}
