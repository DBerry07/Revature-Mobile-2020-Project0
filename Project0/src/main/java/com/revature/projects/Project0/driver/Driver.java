package com.revature.projects.Project0.driver;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.projects.Project0.doa.*;
import com.revature.projects.Project0.pojo.*;
import com.revature.projects.Project0.service.*;

public class Driver {
	
	private static Scanner scan = new Scanner(System.in);

	public static CarDOA cDOA = new CarDOA();
	public static UserDOA uDOA = new UserDOA();

	public static void main(String[] args) {
		String option = "";
		int selection = 0;

		do {
			System.out.println("\nWelcome to Car World!");
			System.out.println("Please select an option:");
			System.out.println("[1] Log In as User\n[2] Log In as Admin" + "\n"
					+ "[3] Create new User Account\n[9] Exit\n");
			option = scan.nextLine();
			
			try {
				selection = Integer.parseInt(option);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			if (selection == 1) {
				System.out.println("Enter username: ");
				String username = scan.nextLine();
				System.out.println("Enter Password: ");
				String password = scan.nextLine();
				boolean check = UserLoginService.checkUser(username, password);
				if (check ==  false) {
					System.out.println("\nInvalid Credentials");
					continue;
				}
				else if (check == true) {
					System.out.println("\nCredentials Accepted!");
					continue;
				}
			}
			
			else if (selection == 3) {
				System.out.println("Enter the desired username: ");
				String username = scan.nextLine();
				System.out.println("Enter the desired password: ");
				String password = scan.nextLine();
				
				boolean res = UserLoginService.createUser(username, password);
				if (res == true) {
					System.out.println("Success! New user '" + username + "' has been created!");
				}
				else {
					System.out.println("Failure! Invalid username!");
				}
			}
			
		} while (selection != 9);
		
		System.out.println("Exiting...");
		
	}

}
