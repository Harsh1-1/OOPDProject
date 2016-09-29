package models;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.User;

public class HealthData {

	public void newData(int distanceRun, int caloriesBurned, int systolicBP, int diastolicBP,User curUser)
	{
		
	}
	
	//to whether user data exists or not
	public boolean ifDataExists(User curUser)
	{
		return false;
	}
	
	public ArrayList<String> getData(String userName)
	{
		ArrayList<String> userdata = new ArrayList<String>();
		
		return userdata;
	}
	
}
