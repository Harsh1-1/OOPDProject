package smart;

import java.util.Scanner;

public class EndUser extends User{
	private int Karma;
	Scanner in = new Scanner(System.in);
	public EndUser(String FirstName, String LastName,String PrimaryEmail,
			String SecondaryEmail, String Password, String UserId,
			String PostalAddress, String AboutMe, String picurl1,
			String picurl2, String picurl3)
	{
		super(FirstName, LastName, PrimaryEmail, SecondaryEmail, Password, UserId, PostalAddress, AboutMe, picurl1, picurl2, picurl3);
		this.Karma = 0;
		this.UserType="ENDUSER";
	}
	
	@Override
	public void displayProfileInfo()
	{
		super.displayProfileInfo();
		System.out.println("User Karma: " + this.Karma);
	}
}
