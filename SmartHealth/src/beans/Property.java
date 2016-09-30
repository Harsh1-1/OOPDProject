package beans;

public class Property {

	int id;
	String name;
	String description;
	
	public Property(int id, String name, String description)
	{
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public String getPropertyName()
	{
		return name;
	}
	
	public int getPropertyId()
	{
		return id;
	}
	
	public String getPropertyDescription()
	{
		return description;
	}
}
