package smart;

import java.util.Scanner;

public class Friends extends State{
	

	private models.Friends model = new models.Friends();
	
	Friends(Scanner sc)
	{
		super(sc);
	}
	
	//handler function for friends
	State handle()
	{
		System.out.println("What you want to do:");
		System.out.println("1. View Friends");
		System.out.println("2. Send Friend Request");
		System.out.println("3. View pending requests");
		System.out.println("4. Unfriend a friend");
		System.out.println("5. Withdraw sent requests");
		System.out.println("6. Go back to profile page");
		
		int choice = sc.nextInt();
		
		switch(choice)
		{
		case 1:
			model.viewFriends(SmartHealth.curUser);
			break;
		case 2:
			System.out.println("Enter the username of the user,"
					+ "you want to add as a friend: ");
		String UserName = sc.next();
		if(UserName.equals(SmartHealth.curUser.getUserId()))
		{
			System.out.println("you cannot send friend request to your self :P");
			break;
		}
		if(sendFriendRequest(UserName))
			System.out.println("Friend request sent successfully to:" + UserName);
		else
			System.out.println("The above error occured while sending request!!");
			break;
		case 3: 
			model.viewPendingRequests(SmartHealth.curUser);
			break;
		case 4:
			unfriend();
			break;
		case 5:
			model.withdrawRequests(SmartHealth.curUser);
			break;
		case 6:
			return new LoggedIn(sc);
		default: System.out.println("Invalid choice entered");
		}
		 //just to avoid error as of now
		return this;
	}
	
	
	private boolean sendFriendRequest(String UserName)
	{
		if(model.ifUserExists(UserName))
		{
			if(model.isalreadyafriend(UserName,SmartHealth.curUser))
			{
				System.out.println(UserName + " is already your friend!!");
				return false;
			}
			
			if(!model.isEndUser(UserName))
			{
				System.out.println("Cannot send friend request to hidden user");
				return false;
			}
				
			
			return model.sendFriendRequest(UserName, SmartHealth.curUser);
		}
		else
		{
			System.out.println("Username doesn't exists!!");
			return false;
		}
		
	}
	
	//to unfriend an existing friend
	private void unfriend()
	{
		System.out.println("Here are your all friends:");
		model.viewFriends(SmartHealth.curUser);
		System.out.println("Enter username of friend you want to unfriend:");
		String UserName = sc.next();
		if(model.ifUserExists(UserName))
		{
			if(model.isalreadyafriend(UserName,SmartHealth.curUser))
			{
				
				model.unfriend(UserName, SmartHealth.curUser);
				
			}
			else
				System.out.println("You cannot unfriend someone who is not your friend");
		}
		else
		{
			System.out.println("UserName does not exist!!");
		}
	}
	
	
	
	
}
