package smart;

//Admin class inherits abstract class User
public class Admin extends User{	
	
	private String EmergencyContact;
	
	//Constructor for initialization of current user object
	public Admin(String FirstName, String LastName,String PrimaryEmail,
			String SecondaryEmail, String Password, String UserId,
			Address Address, String AboutMe, String picurl1,
			String picurl2, String picurl3,boolean hasQuit, String EmergencyContact)
	{
		super(FirstName, LastName, PrimaryEmail, SecondaryEmail, Password, UserId, Address, AboutMe, picurl1, picurl2, picurl3, hasQuit);
		this.EmergencyContact = EmergencyContact;
		this.UserType="ADMIN";
	}
	
	//setter for emergency contact no.
	public void setEmergencyContact(String EmergencyContact)
	{
		this.EmergencyContact = EmergencyContact;
	}
	public String getEmergencyContact(){
		return EmergencyContact;
	}
	//for displaying Admin's info, overrides base class User display method
	@Override
	public void displayProfileInfo()
	{
		super.displayProfileInfo();
		System.out.println("Emergency Contact No: " + this.EmergencyContact);
	}
}
