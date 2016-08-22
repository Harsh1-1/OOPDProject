package smart;

import java.util.Scanner;

public class Enduser extends user{
	private int Karma;
	private String UserType;
	Scanner in = new Scanner(System.in);
	public Enduser(String FirstName, String LastName,String PrimaryEmail,
			String SecondaryEmail, String Password, String UserId,
			String PostalAddress, String AboutMe, String picurl1,
			String picurl2, String picurl3,int Karma, String UserType)
	{
		super(FirstName, LastName, PrimaryEmail, SecondaryEmail, Password, UserId, PostalAddress, AboutMe, picurl1, picurl2, picurl3);
		this.Karma = Karma;
		this.UserType = UserType;
}
	
}
