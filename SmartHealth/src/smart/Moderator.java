package smart;

import java.util.*;

//Moderator class inherits abstract class User
public class Moderator extends User{
	
	private String EmergencyContact;
	private ArrayList<Qualification> Qualifications;
	
	//Constructor for initialization of Moderator Object
	public	Moderator(String FirstName, String LastName,String PrimaryEmail,
			String SecondaryEmail, String Password, String UserId,
			Address Address, String AboutMe, String picurl1,
			String picurl2, String picurl3, boolean hasQuit,String EmergencyContact, ArrayList<Qualification> Qualifications)
	{
		super(FirstName, LastName, PrimaryEmail, SecondaryEmail, Password, UserId, Address, AboutMe, picurl1, picurl2, picurl3, hasQuit);
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
	public void setQualifications(ArrayList<Qualification> qualifications){
		this.Qualifications = qualifications;
	}
	
	public String getEmergencyContact(){
		return EmergencyContact;
	}
	public ArrayList<Qualification> getQualifications(){
		return Qualifications;
	}
	//functions for displaying Moderator's info, overrides base class User dispayProfileInfo method
	@Override
	public void displayProfileInfo()
	{
		super.displayProfileInfo();
		System.out.println("Emergency Contact No: " + this.EmergencyContact);
		for(int i=0;i<this.Qualifications.size();i++)
		{
			System.out.println("Qualifications are : " + this.Qualifications.get(i).toString());
		}
	}
}
