package beans;

//End User inherits abstract base class User
public class EndUser extends User{
	private int karma;
	
	//Constructor for initialization of End User object
	public EndUser(String firstName, String lastName,String primaryEmail,
			String secondaryEmail, String password, String userId,
			Address address, String aboutMe, String picurl1,
			String picurl2, String picurl3, boolean hasQuit)
	{
		super(firstName, lastName, primaryEmail, secondaryEmail, password, userId, address, aboutMe, picurl1, picurl2, picurl3, hasQuit);
		this.karma = 0;
		this.userType="ENDUSER";
	}
	
	//functions for displaying End User's info, overrides base class User dispayProfileInfo method
	@Override
	public void displayProfileInfo()
	{
		super.displayProfileInfo();
		System.out.println("User Karma: " + this.karma);
	}
	
	public int getKarma(){
		return karma;
	}
	public void setKarma(int karma){
		this.karma = karma;
	}
}
