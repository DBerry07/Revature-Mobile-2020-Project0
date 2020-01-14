package com.revature.projects.Project0.service;

import java.util.ArrayList;

import com.revature.projects.Project0.dao.UserDAO;
import com.revature.projects.Project0.pojo.User;

public class UserLoginService {

	private static UserDAO uDAO = new UserDAO();
	private static String filename = "users.dat";

	public static boolean checkUser(String username, String password) {
		ArrayList<User> users = (ArrayList<User>) uDAO.readUsers();
		if (users == null) {
			return false;
		}
		for (User each : users) {
			if (each.getUsername().equals(username)) {
				if (each.getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean checkAdmin(String username, String password) {
		ArrayList<User> users = (ArrayList<User>) uDAO.readUsers();
		if (users == null) {
			return false;
		}
		for (User each : users) {
			if (each.getUsername().equals(username) && each.getPassword().equals(password)) {
				if (each.getAdmin() == true) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean createUser(String newUsername, String newPassword, boolean admin) {

		ArrayList<User> users = (ArrayList<User>) uDAO.readUsers();

		try {
			for (User each : users) {
				if (each.getUsername().equals(newUsername)) {
					return false;
				}
			}
			users.add(new User(newUsername, newPassword, admin));
		} catch (NullPointerException e) {
			users = new ArrayList<User>();
			users.add(new User(newUsername, newPassword, admin));
		}
		uDAO.writeObject(users);
		return true;
	}
	
	public static User getUser(String username, String password) {
		ArrayList<User> users = (ArrayList<User>) uDAO.readUsers();
		
		try {
			for (User each : users) {
				if (each.getUsername().equals(username) && each.getPassword().equals(password)) {
					return each;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
