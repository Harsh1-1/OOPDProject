package smart;

//Abstract class user inherited by Admin, Moderator and EndUser Classes
public abstract class User {

	//All the required data of user
	private String PrimaryEmail;
	private String SecondaryEmail;
	private String Password;
	private String UserId;
	private String FirstName;
	private String LastName;
	private Address Address;
	private String AboutMe;
	private String picurl[] = new String[3];
	protected String UserType;
	private boolean hasQuit;
	
	//Constructor for initialization
	User(String FirstName, String LastName,String PrimaryEmail,
			String SecondaryEmail, String Password, String UserId,
			Address Address, String AboutMe, String picurl1,
			String picurl2, String picurl3, boolean hasQuit)
	{
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.PrimaryEmail = PrimaryEmail;
		this.SecondaryEmail = SecondaryEmail;
		this.Password = Password;
		this.UserId = UserId;
		this.Address = Address;
		this.AboutMe = AboutMe;
		this.picurl[0] = picurl1;
		this.picurl[1] = picurl2;
		this.picurl[2] = picurl3;
		this.hasQuit = hasQuit;
	}
	
	//All the getters for data
	public String getUserType()
	{
		return this.UserType;
	}
	
	
	public String getPrimaryEmail()
	{
		return this.PrimaryEmail;
	}
	
	public String getSecondaryEmail()
	{
		return this.SecondaryEmail;
	}

	protected String getPassword()
	{
		return this.Password;
	}
	
	public String getUserId()
	{
		return this.UserId;
	}
	
	public String getFirstName()
	{
		return this.FirstName;
	}
	
	public String getLastName()
	{
		return this.LastName;
	}
	
	public Address getPostalAddress()
	{
		return this.Address;
	}
	
	public String getAboutMe()
	{
		return this.AboutMe;
	}
	
	public String[] getPicURL()
	{
		return this.picurl;
	}
	
	
	//All the setters for data
	public void setUserType(String UserType)
	{
		this.UserType = UserType;
	}
	
	
	public void setPrimaryEmail(String PrimaryEmail)
	{
		
		this.PrimaryEmail = PrimaryEmail;
		
	}
	
	public void setSecondaryEmail(String SecondaryEmail)
	{
		
		this.SecondaryEmail = SecondaryEmail;
	}

	protected void setPassword(String Password)
	{
		
		this.Password = Password;
	}
	
	public void setUserId(String UserId)
	{
		
		this.UserId = UserId;
	}
	
	public void setFirstName(String FirstName)
	{
		
		this.FirstName = FirstName;
	}
	
	public void setLastName(String LastName)
	{
		this.LastName = LastName;
	}
	
	public void setPostalAddress(Address Address)
	{	
		this.Address = Address;	
	}
	
	public void setAboutMe(String AboutMe)
	{
		this.AboutMe = AboutMe;
	}
	
	public void setPicURL(String picurl[])
	{
		this.picurl[0] = picurl[0];
		this.picurl[1] = picurl[1];
		this.picurl[2] = picurl[2];
	}
	
	//function called when the user want to quit
	public void quit()
	{
		if(this.hasQuit == false)
			this.hasQuit=true;
	}
	
	//function called if the user want to join again
	public void join()
	{
		this.hasQuit = false;
	}
	
	//for returning has quit
	public boolean hasQuit(){
		return hasQuit;
	}
	
	//For displaying the info
	public void displayProfileInfo()
	{
		System.out.println("User Type: " + this.UserType);
		System.out.println("Primary Email: " + this.PrimaryEmail);
		System.out.println("Secondary Email: " + this.SecondaryEmail);
		System.out.println("User ID: " + this.UserId);
		System.out.println("First Name: " + this.FirstName);
		System.out.println("Last Name: " + this.LastName);
		System.out.println("Postal Address: " + this.Address.toString());
		System.out.println("About: " + this.AboutMe);
		System.out.println("Pic 1: " + this.picurl[0]);
		System.out.println("Pic 2: " + this.picurl[1]);
		System.out.println("Pic 3: " + this.picurl[2]);
	}
	
	
	
	
	
	
}
