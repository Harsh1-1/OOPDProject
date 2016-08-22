package smart;

public class user {

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
	private boolean has_quit;  //needed to be handled somehow 
	
	user(String FirstName, String LastName,String PrimaryEmail,
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
}
