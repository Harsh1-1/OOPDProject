package smart;

public abstract class User {

	private String UserName;
	private String PrimaryEmail;
	private String SecondaryEmail;
	private String Password;
	private String UserId;
	private String FirstName;
	private String LastName;
	private String PostalAddress;
	private String AboutMe;
	private String picurl[] = new String[3];
	protected String UserType;
	private boolean has_quit=false;  //needed to be handled somehow 
	
	User(String FirstName, String LastName,String PrimaryEmail,
			String SecondaryEmail, String Password, String UserId,
			String PostalAddress, String AboutMe, String picurl1,
			String picurl2, String picurl3)
	{
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.PrimaryEmail = PrimaryEmail;
		this.SecondaryEmail = SecondaryEmail;
		this.Password = Password;
		this.UserId = UserId;
		this.PostalAddress = PostalAddress;
		this.AboutMe = AboutMe;
		this.picurl[0] = picurl1;
		this.picurl[1] = picurl2;
		this.picurl[2] = picurl3;
	}
	public String getUserType()
	{
		return this.UserType;
	}
	
	public String getUserName()
	{
		return this.UserName;
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
	
	public String getPostalAddress()
	{
		return this.PostalAddress;
	}
	
	public String getAboutMe()
	{
		return this.AboutMe;
	}
	
	public String[] getPicURL()
	{
		return this.picurl;
	}
	
	
	
	public void setUserType(String UserType)
	{
		this.UserType = UserType;
	}
	
	public void setUserName(String UserName)
	{
	
		this.UserName = UserName;
		
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
	
	public void setPostalAddress(String PostalAddress)
	{
		
	this.PostalAddress = PostalAddress;	
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
	
	public void quit()
	{
		if(this.has_quit == false)
			this.has_quit=true;
	}
	
	public void join()
	{
		this.has_quit = false;
	}
	
	public boolean hasQuit(){
		return has_quit;
	}
	
	public void displayProfileInfo()
	{
		System.out.println("User Type: " + this.UserType);
		System.out.println("Username: " + this.UserName );
		System.out.println("Primary Email: " + this.PrimaryEmail);
		System.out.println("Secondary Email: " + this.SecondaryEmail);
		System.out.println("User ID: " + this.UserId);
		System.out.println("First Name: " + this.FirstName);
		System.out.println("Last Name: " + this.LastName);
		System.out.println("Postal Address: " + this.PostalAddress);
		System.out.println("About: " + this.AboutMe);
		System.out.println("Pic 1: " + this.picurl[0]);
		System.out.println("Pic 2: " + this.picurl[1]);
		System.out.println("Pic 3: " + this.picurl[2]);
	}
	
	
	
	
	
	
}
