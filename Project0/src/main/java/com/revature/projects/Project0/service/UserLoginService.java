package com.revature.projects.Project0.service;

import java.util.ArrayList;

import com.revature.projects.Project0.doa.UserDOA;
import com.revature.projects.Project0.pojo.User;

public class UserLoginService {

	private static UserDOA uDOA = new UserDOA();

	public static boolean checkUser(String username, String password) {
		String fileName = "users.dat";
		
		ArrayList<User> users = (ArrayList<User>) uDOA.readObject(fileName);
		for (User each : users) {
			if (each.getUsername().equals(username)) {
				if (each.getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean createUser(String newUsername, String newPassword) {

		String fileName = "users.dat";
		ArrayList<User> users = (ArrayList<User>) uDOA.readObject(fileName);
		
		try {
		for (User each : users) {
			if (each.getUsername().equals(newUsername)) {
				return false;
			}
		}
		users.add(new User(newUsername, newPassword));
		}
		catch (NullPointerException e) {
			users = new ArrayList<User>();
			users.add(new User(newUsername, newPassword));
		}
		uDOA.writeObject(users);
		return true;
	}
}
