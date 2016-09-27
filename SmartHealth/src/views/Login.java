package views;

import java.util.Scanner;

public class Login {
	Scanner sc;
	public int getchoice(){
		//Print available choices
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("Enter your choice ");
		int choice = sc.nextInt();
		return choice;
	}
	public String getPrimaryEmailID(){
		System.out.println("Enter your Primary Email ID : ");
		String emailID = sc.next();
		return emailID;
	}
	public String getPassword(){
		System.out.println("Enter your Password : ");
		String password = sc.next();
		return password;
	}
	public void setError(String error){
		System.out.println(error);
	}
	public Login(){
		sc = new Scanner(System.in);
	}
}
