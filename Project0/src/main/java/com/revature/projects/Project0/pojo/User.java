package com.revature.projects.Project0.pojo;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class User implements Serializable{
	
	private String username;
	private String password;
	private ArrayList<Car> carsBought;
	
	public User() {} //No args constructor
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}

}
