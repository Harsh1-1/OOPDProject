package smart;

import java.util.*;

public class Moderator extends User{
	
	String EmergencyContact;
	ArrayList<String> Qualifications; //needed to be handled
	Scanner in = new Scanner(System.in);
	public	Moderator(String FirstName, String LastName,String PrimaryEmail,
			String SecondaryEmail, String Password, String UserId,
			String PostalAddress, String AboutMe, String picurl1,
			String picurl2, String picurl3, String EmergencyContact)
	{
		super(FirstName, LastName, PrimaryEmail, SecondaryEmail, Password, UserId, PostalAddress, AboutMe, picurl1, picurl2, picurl3);
		this.EmergencyContact = EmergencyContact;
		this.UserType="MOD";
	}
	public void setEmergencyContact(String EmergencyContact)
	{
		this.EmergencyContact = EmergencyContact;
	}
}
