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
	private boolean has_quit;  //need to handle somehow 
	
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
