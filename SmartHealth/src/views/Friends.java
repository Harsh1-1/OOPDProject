package views;

import java.util.Scanner;

public class Friends {

	Scanner sc;
	
	public Friends()
	{
		sc = new Scanner(System.in);
	}
	
	public int acceptreject(String requesterUsername)
	{
		System.out.println("1. Accept " + requesterUsername + " as friend");
		System.out.println("2. Reject " + requesterUsername + " as friend");
		System.out.println("3. Cancel");
		System.out.println("Enter your choice:");
		int choice = sc.nextInt();
		return choice;
	}
	
	public int getwithdrawchoice()
	{
		System.out.println("want to withdraw request for this user ? Enter 1 for yes 0 for no");
		int choice = sc.nextInt();
		return choice;
	}
}
