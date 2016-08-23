package smart;


import java.util.*;

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
	private String UserType;
	private boolean has_quit;  //needed to be handled somehow 
	
	Scanner in = new Scanner(System.in);
	
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
		this.picurl[1] = picurl1;
		this.picurl[2] = picurl2;
		this.picurl[3] = picurl3;
	}
	public String getusertype()
	{
		return this.UserType;
	}
	
	public String getusername()
	{
		return this.UserName;
	}
	
	public String getprimaryemail()
	{
		return this.PrimaryEmail;
	}
	
	public String getsecondaryemail()
	{
		return this.SecondaryEmail;
	}

	protected String getpassword()
	{
		return this.Password;
	}
	
	public String getuserid()
	{
		return this.UserId;
	}
	
	public String getfirstname()
	{
		return this.FirstName;
	}
	
	public String getlastname()
	{
		return this.LastName;
	}
	
	public String getpostaladdress()
	{
		return this.PostalAddress;
	}
	
	public String getaboutme()
	{
		return this.AboutMe;
	}
	
	public String[] getpicurl()
	{
		return this.picurl;
	}
	
	
	
	public void setusertype(String UserType)
	{
		this.UserType = UserType;
	}
	
	public void setusername(String UserName)
	{
	
		this.UserName = UserName;
		
	}
	
	public void setprimaryemail(String PrimaryEmail)
	{
		
		this.PrimaryEmail = PrimaryEmail;
		
	}
	
	public void setsecondaryemail(String SecondaryEmail)
	{
		
		this.SecondaryEmail = SecondaryEmail;
	}

	protected void setpassword(String Password)
	{
		
		this.Password = Password;
	}
	
	public void setuserid(String UserId)
	{
		
		this.UserId = UserId;
	}
	
	public void setfirstname(String FirstName)
	{
		
		this.FirstName = FirstName;
	}
	
	public void setlastname(String LastName)
	{
		
		this.LastName = LastName;
		
	}
	
	public void setpostaladdress(String PostalAddress)
	{
		
	this.PostalAddress = PostalAddress;	
	}
	
	public void setaboutme(String AboutMe)
	{
		
		this.AboutMe = AboutMe;
	}
	
	
	
	
	
	
}
