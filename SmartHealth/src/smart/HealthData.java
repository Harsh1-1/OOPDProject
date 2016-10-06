package smart;

import java.util.ArrayList;
import java.util.Scanner;

import beans.Property;

public class HealthData extends State{

	private models.HealthData model = new models.HealthData();
	private models.Friends friendsModel = new models.Friends();
	
	
	HealthData(Scanner sc)
	{
		super(sc);
	}
	
	State handle()
	{
		System.out.println("What you want to do ?");
		System.out.println("1. Enter your new Health data");
		System.out.println("2. View your Health data");
		System.out.println("3. View Health data of your friend");
		System.out.println("4. Go back to profile page");
		System.out.println("5. Logout");
		
		int choice = sc.nextInt();
		
		switch(choice)
		{
		case 1:
			ArrayList<Property> healthProperties = model.getProperties();
			ArrayList<Integer> healthPropertiesValues = new ArrayList<Integer>();
			System.out.println("Instruction: Please enter all the data below as integer");
			for(int i = 0; i < healthProperties.size(); i++)
			{
				System.out.println("Enter " + healthProperties.get(i).getPropertyName() + ":" );
				int propertyValue = sc.nextInt();
				healthPropertiesValues.add(propertyValue);
			}
			model.newData(healthPropertiesValues, SmartHealth.curUser);
			break;
		case 2:
			if(model.ifDataExists(SmartHealth.curUser))
			{
				ArrayList<String> userData = model.getData(SmartHealth.curUser.getUserId());
				
				System.out.println("Distance you have run: " + userData.get(0) );
				System.out.println("Calories Burned: " + userData.get(1));
				System.out.println("Systolic Blood Pressure: " + userData.get(2));
				System.out.println("Diastolic Blood Pressure: " + userData.get(3));
			} 
			else
			{
				System.out.println("No previous Health data exists, do you want to enter new one ? "
						+ "enter 1 for yes 0 for no:");
				
				int option = sc.nextInt();
				if(option == 1)
				{
					ArrayList<Property> healthProperties1 = model.getProperties();
					ArrayList<Integer> healthPropertiesValues1 = new ArrayList<Integer>();
					System.out.println("Instruction: Please enter all the data below as integer");
					for(int i = 0; i < healthProperties1.size(); i++)
					{
						System.out.println("Enter " + healthProperties1.get(i).getPropertyName() + ":" );
						int propertyValue = sc.nextInt();
						healthPropertiesValues1.add(propertyValue);
					}
					model.newData(healthPropertiesValues1, SmartHealth.curUser);
				}
				else if(option == 0)
					System.out.println("No HealthData added");
				else
					System.out.println("Wrong choice, no new health data added");
			}
			break;
		case 3:
			System.out.println("Enter the username of friend, whose health data you want to see:");
			String userName = sc.next();
			if(friendsModel.ifUserExists(userName))
			{
				if(friendsModel.isalreadyafriend(userName, SmartHealth.curUser))
				{
					ArrayList<String> userData = model.getData(userName);
					
					System.out.println("Distance ran by" + userName + ": " + userData.get(0) );
					System.out.println("Calories Burned by him: " + userData.get(1));
					System.out.println("Systolic Blood Pressure of him: " + userData.get(2));
					System.out.println("Diastolic Blood Pressure of him: " + userData.get(3));
				}
				else
				{
					System.out.println("You cannot see Health data of a non-frined!!");
				}
			}
			else
			{
				System.out.println("Username does'nt exist!!!");
			}
			
			break;
		case 4:
			return new LoggedIn(sc);
		case 5:
			SmartHealth.curUser = null;
			return new Login(sc);
		default:System.out.println("wrong choice entered");
		}
		
		return this;
	}
}
