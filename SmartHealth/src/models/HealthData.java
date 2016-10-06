package models;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Property;
import beans.User;
import smart.Global;

public class HealthData {

	public void newData(ArrayList<Integer> healthPropertiesValues, User curUser)
	{
		try
		{
			Connection con = DriverManager.getConnection(Global.connectionString);
			Statement stmt = con.createStatement();
			String SQL;
			int j;
			for(int i =0; i < healthPropertiesValues.size(); i++ )
			{
				 j=i+1;
				 SQL = "insert into datum values(0,'"
						+ curUser.getUserId() + "',"
								+ j + ",'" + healthPropertiesValues.get(i) + "',"
								+ "NOW() );";
				int result = stmt.executeUpdate(SQL);
				
				if(result == 0)
				{
					System.out.println("Some error occurred while inserting data");
					break;
				}
				else
					System.out.println("Data "+ j + " inserted successfully");
			}
			
			stmt.close();
			con.close();
		}
		
		catch ( SQLException err) {
			System.out.println(err.getMessage());
		}
	}
	
	//to whether user data exists or not
	public boolean ifDataExists(User curUser)
	{
		try
		{
			Connection con = DriverManager.getConnection(Global.connectionString);
			Statement stmt = con.createStatement();
			String SQL = "select  1 from datum where Username = '"
					+ curUser.getUserId() + "';";
			ResultSet result = stmt.executeQuery(SQL);
			boolean ifdataexists = result.isBeforeFirst();
			
			result.close();
			stmt.close();
			con.close();
			return ifdataexists;
		}
		
		catch ( SQLException err) {
			System.out.println(err.getMessage());
		}
		return false;
	}
	
	public ArrayList<String> getData(String userName)
	{
		
		ArrayList<String> userdata = new ArrayList<String>();
		
		try
		{
			Connection con = DriverManager.getConnection(Global.connectionString);
			Statement stmt = con.createStatement();
			
			String SQL = "select t1.Value from smarthealthdb.datum as t1, (select Username,value,max(WhenSaved) as savedtime,PropertyID from smarthealthdb.datum group by Username,PropertyID having Username = '"+ userName + "') as maxsaved where t1.Username = maxsaved.Username and t1.PropertyID = maxsaved.PropertyID and t1.WhenSaved = maxsaved.savedtime order by t1.PropertyID;";
			
			
			ResultSet result = stmt.executeQuery(SQL);
			
			boolean ifdataexist = result.isBeforeFirst();
			
			if(!ifdataexist)
			{
				userdata=null;
				return userdata;
			}
			
			while(result.next())
			{
			
				String value = result.getString("Value");
				userdata.add(value);
				
			}
			
		result.close();
		stmt.close();
		con.close();
		}
		catch ( SQLException err) {
			System.out.println(err.getMessage());
		}
		return userdata;
	}
	
	public ArrayList<Property> getProperties()
	{
		ArrayList<Property> healthProperties = new ArrayList<Property>();
		
		try
		{
			Connection con = DriverManager.getConnection(Global.connectionString);
			Statement stmt = con.createStatement();
			String SQL = "select * from property";
			ResultSet result = stmt.executeQuery(SQL);
			
			while(result.next())
			{
				int propertyID = result.getInt("PropertyID");
				String name = result.getString("Name");
				String description = result.getString("Description");
				
				healthProperties.add(new Property(propertyID,name,description));
				
			}
			
			result.close();
			stmt.close();
			con.close();
		}
		
		catch ( SQLException err) {
			System.out.println(err.getMessage());
		}
		
		
		return healthProperties;
	}
	
}
