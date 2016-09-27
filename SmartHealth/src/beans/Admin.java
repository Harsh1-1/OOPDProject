package beans;

//Admin class inherits abstract class User
public class Admin extends User{	
	
	private String emergencyContact;
	
	//Constructor for initialization of current user object
	public Admin(String firstName, String lastName,String primaryEmail,
			String secondaryEmail, String password, String userId,
			Address address, String aboutMe, String picurl1,
			String picurl2, String picurl3,boolean hasQuit, String emergencyContact)
	{
		super(firstName, lastName, primaryEmail, secondaryEmail, password, userId, address, aboutMe, picurl1, picurl2, picurl3, hasQuit);
		this.emergencyContact = emergencyContact;
		this.userType="ADMIN";
	}
	
	//setter for emergency contact no.
	public void setEmergencyContact(String emergencyContact)
	{
		this.emergencyContact = emergencyContact;
	}
	public String getEmergencyContact(){
		return emergencyContact;
	}
	//for displaying Admin's info, overrides base class User display method
	@Override
	public void displayProfileInfo()
	{
		super.displayProfileInfo();
		System.out.println("Emergency Contact No: " + this.emergencyContact);
	}
}
