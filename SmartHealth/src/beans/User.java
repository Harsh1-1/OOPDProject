package beans;

//Abstract class user inherited by Admin, Moderator and EndUser Classes
public abstract class User {

	//All the required data of user
	private String primaryEmail;
	private String secondaryEmail;
	private String password;
	private String userId;
	private String firstName;
	private String lastName;
	private Address address;
	private String aboutMe;
	private String picurl[] = new String[3];
	protected String userType;
	private boolean hasQuit;
	
	//Constructor for initialization
	User(String firstName, String lastName,String primaryEmail,
			String secondaryEmail, String password, String userId,
			Address address, String aboutMe, String picurl1,
			String picurl2, String picurl3, boolean hasQuit)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryEmail = primaryEmail;
		this.secondaryEmail = secondaryEmail;
		this.password = password;
		this.userId = userId;
		this.address = address;
		this.aboutMe = aboutMe;
		this.picurl[0] = picurl1;
		this.picurl[1] = picurl2;
		this.picurl[2] = picurl3;
		this.hasQuit = hasQuit;
	}
	
	//All the getters for data
	public String getUserType()
	{
		return this.userType;
	}
	
	
	public String getPrimaryEmail()
	{
		return this.primaryEmail;
	}
	
	public String getSecondaryEmail()
	{
		return this.secondaryEmail;
	}

	public String getPassword()
	{
		return this.password;
	}
	
	public String getUserId()
	{
		return this.userId;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public Address getPostalAddress()
	{
		return this.address;
	}
	
	public String getAboutMe()
	{
		return this.aboutMe;
	}
	
	public String[] getPicURL()
	{
		return this.picurl;
	}
	
	
	//All the setters for data
	public void setUserType(String userType)
	{
		this.userType = userType;
	}
	
	
	public void setPrimaryEmail(String primaryEmail)
	{
		
		this.primaryEmail = primaryEmail;
		
	}
	
	public void setSecondaryEmail(String secondaryEmail)
	{
		
		this.secondaryEmail = secondaryEmail;
	}

	public void setPassword(String password)
	{
		
		this.password = password;
	}
	
	public void setUserId(String userId)
	{
		
		this.userId = userId;
	}
	
	public void setFirstName(String firstName)
	{
		
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public void setPostalAddress(Address address)
	{	
		this.address = address;	
	}
	
	public void setAboutMe(String aboutMe)
	{
		this.aboutMe = aboutMe;
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
		System.out.println("User Type: " + this.userType);
		System.out.println("Primary Email: " + this.primaryEmail);
		System.out.println("Secondary Email: " + this.secondaryEmail);
		System.out.println("User ID: " + this.userId);
		System.out.println("First Name: " + this.firstName);
		System.out.println("Last Name: " + this.lastName);
		System.out.println("Postal Address: " + this.address.toString());
		System.out.println("About: " + this.aboutMe);
		System.out.println("Pic 1: " + this.picurl[0]);
		System.out.println("Pic 2: " + this.picurl[1]);
		System.out.println("Pic 3: " + this.picurl[2]);
	}
	
}
