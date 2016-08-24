package smart;

import java.util.*;

//Moderator class inherits abstract class User
public class Moderator extends User{
	
	private String EmergencyContact;
	private ArrayList<String> Qualifications;
	
	//Constructor for initialization of Moderator Object
	public	Moderator(String FirstName, String LastName,String PrimaryEmail,
			String SecondaryEmail, String Password, String UserId,
			String PostalAddress, String AboutMe, String picurl1,
			String picurl2, String picurl3, String EmergencyContact, ArrayList<String> Qualifications)
	{
		super(FirstName, LastName, PrimaryEmail, SecondaryEmail, Password, UserId, PostalAddress, AboutMe, picurl1, picurl2, picurl3);
		this.EmergencyContact = EmergencyContact;
		this.UserType="MOD";
		this.Qualifications = Qualifications;
	}
	
	//Setter for Emergency Contact
	public void setEmergencyContact(String EmergencyContact)
	{
		this.EmergencyContact = EmergencyContact;
	}
	
	//Setter for Qualifications
	public void setQualifications(ArrayList<String> qualifications){
		this.Qualifications = qualifications;
	}
	
	//functions for displaying Moderator's info, overrides base class User dispayProfileInfo method
	@Override
	public void displayProfileInfo()
	{
		super.displayProfileInfo();
		System.out.println("Emergency Contact No: " + this.EmergencyContact);
		for(int i=0;i<this.Qualifications.size();i++)
		{
			System.out.println("Qualifications are : " + this.Qualifications.get(i));
		}
	}
}
