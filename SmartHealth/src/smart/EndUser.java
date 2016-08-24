package smart;

//End User inherits abstract base class User
public class EndUser extends User{
	private int Karma;
	
	//Constructor for initialization of End User object
	public EndUser(String FirstName, String LastName,String PrimaryEmail,
			String SecondaryEmail, String Password, String UserId,
			String PostalAddress, String AboutMe, String picurl1,
			String picurl2, String picurl3)
	{
		super(FirstName, LastName, PrimaryEmail, SecondaryEmail, Password, UserId, PostalAddress, AboutMe, picurl1, picurl2, picurl3);
		this.Karma = 0;
		this.UserType="ENDUSER";
	}
	
	//functions for displaying End User's info, overrides base class User dispayProfileInfo method
	@Override
	public void displayProfileInfo()
	{
		super.displayProfileInfo();
		System.out.println("User Karma: " + this.Karma);
	}
}
